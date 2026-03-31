package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;

/**
 * Generic base controller providing common CRUD operations for all ORS controllers.
 * <p>
 * This abstract-style class is parameterized with a DTO type {@code T}, a service
 * interface {@code S}, and a form type {@code F}, allowing reuse of save, delete,
 * find, and search functionality across different modules.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 * @param <S> the service type extending {@link BaseServiceInt}
 * @param <F> the form type extending {@link BaseForm}
 *
 * @author Krati Trivedi
 */
public class BaseCtl<T extends BaseDTO, S extends BaseServiceInt<T>, F extends BaseForm<T>> {

    /**
     * Validates the binding result from a form submission.
     * <p>
     * If there are field-level validation errors, they are collected into a map
     * and attached to the response as input errors.
     * </p>
     *
     * @param bindingResult the {@link BindingResult} from the validated form
     * @return an {@link ORSResponse} indicating success or containing validation errors
     */
    public ORSResponse validate(BindingResult bindingResult) {
        ORSResponse res = new ORSResponse(true);

        if (bindingResult.hasErrors()) {
            res.setSuccess(false);
            Map<String, String> errors = new HashMap<String, String>();
            List<FieldError> list = bindingResult.getFieldErrors();
            list.forEach(e -> {
                errors.put(e.getField(), e.getDefaultMessage());
            });
            res.addInputError(errors);
        }

        return res;
    }

    /**
     * The service instance auto-injected for performing business logic operations.
     */
    @Autowired
    protected S service;

    /**
     * The page size used for paginated search results, injected from application properties.
     */
    @Value("${page.size}")
    private int pageSize = 0;

    /**
     * Holds the current user's context for authorization and audit purposes.
     */
    public UserContext userContext = null;

    /**
     * Initializes the {@link UserContext} before each request.
     * <p>
     * Retrieves the context from {@link UserContextHolder}. If no context is found,
     * a default user context is created using a fallback login.
     * </p>
     */
    @ModelAttribute
    public void setUserContext() {
        userContext = UserContextHolder.getContext();
        if (userContext == null) {
            UserDTO dto = new UserDTO();
            dto.setLogin("krati@gmail.com");
            userContext = new UserContext(dto);
        }
    }

    /**
     * Saves or updates a record based on the submitted form data.
     * <p>
     * Performs validation first. If the DTO has a unique key defined, it checks for
     * duplicate records before proceeding. Returns an appropriate success or failure
     * message depending on whether the operation is an insert or update.
     * </p>
     *
     * @param form          the form object containing DTO data, validated via {@code @Valid}
     * @param bindingResult the result of form validation
     * @return an {@link ORSResponse} indicating the outcome of the save operation
     */
    @PostMapping(value = "save")
    public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {

        ORSResponse res = new ORSResponse();

        res = validate(bindingResult);

        if (!res.success) {
            return res;
        }

        T dto = form.getDto();

        if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {

            T existsDTO = service.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);

            if (existsDTO != null && (dto.getId() == null || !existsDTO.getId().equals(dto.getId()))) {
                res.addMessage(dto.getLabel() + " already exists");
                res.setSuccess(false);
                return res;
            }
        }

        Long exId = dto.getId();

        long id = service.save(dto, userContext);

        if (exId == null) {
            res.addMessage(dto.getTableName() + " added successfully");
            res.addData(dto);
        } else {
            res.addMessage(dto.getTableName() + " updated successfully");
            res.addData(dto);
        }
        return res;
    }

    /**
     * Deletes multiple records by their IDs and returns updated search results.
     * <p>
     * After deletion, performs a search using the provided form criteria to return
     * the remaining records for the given page. Also checks if a next page exists.
     * </p>
     *
     * @param ids    an array of record IDs to be deleted
     * @param pageNo the current page number for refreshing results after deletion
     * @param form   the form containing search criteria for the post-delete result fetch
     * @return an {@link ORSResponse} with the updated list of records or a not-found message
     */
    @PostMapping("deleteMany/{ids}")
    public ORSResponse deleteMany(@PathVariable long[] ids, @RequestParam("pageNo") int pageNo,
            @RequestBody F form) {

        ORSResponse res = new ORSResponse(true);

        for (long id : ids) {
            service.delete(id, userContext);
        }

        T dto = (T) form.getDto();

        List<T> list = service.search(dto, pageNo, pageSize, userContext);
        List<T> nextList = service.search(dto, pageNo + 1, pageSize, userContext);
        if (list.size() == 0) {
            res.setSuccess(false);
            res.addMessage("Record not found..!!");
        } else {
            res.setSuccess(true);
            res.addMessage("Records Deleted Successfully");
            res.addData(list);
            res.addResult("nextListSize", nextList.size());
        }

        return res;
    }

    /**
     * Retrieves a single record by its primary key.
     *
     * @param id the primary key of the record to retrieve
     * @return an {@link ORSResponse} containing the found DTO, or a failure message if not found
     */
    @GetMapping(value = "get/{id}")
    public ORSResponse findByPk(@PathVariable long id) {

        ORSResponse res = new ORSResponse();

        T dto = service.findByPk(id, userContext);

        if (dto != null) {
            res.addMessage("record found");
            res.addData(dto);
            res.setSuccess(true);
        } else {
            res.addMessage("record not found");
            res.addData(dto);
            res.setSuccess(false);
        }

        return res;
    }

    /**
     * Searches for records matching the given form criteria with pagination support.
     * <p>
     * Accepts both GET and POST requests. Returns a paginated list of matching records
     * along with the size of the next page to support client-side pagination controls.
     * </p>
     *
     * @param form   the form containing search/filter criteria
     * @param pageNo the page number to retrieve (0-based); negative values default to 0
     * @return an {@link ORSResponse} containing the matching records or a not-found message
     */
    @RequestMapping(value = "search/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
    public ORSResponse search(@RequestBody F form, @PathVariable(required = false) int pageNo) {

        ORSResponse res = new ORSResponse();

        T dto = form.getDto();
        pageNo = (pageNo < 0) ? 0 : pageNo;

        int pageSize = 5;

        List<T> list = service.search(dto, pageNo, pageSize, userContext);
        List<T> nextListSize = service.search(dto, pageNo + 1, pageSize, userContext);

        if (list.size() > 0) {
            res.addMessage("records found");
            res.setSuccess(true);
            res.addData(list);
            res.addResult("nextListSize", nextListSize);
        } else {
            res.addMessage("no record found");
            res.setSuccess(false);
        }
        return res;
    }
}