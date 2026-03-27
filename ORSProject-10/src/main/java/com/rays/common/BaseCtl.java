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

public class BaseCtl<T extends BaseDTO, S extends BaseServiceInt<T>, F extends BaseForm<T>> {

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
	
	
	@Autowired
	protected S service;
	
	@Value("${page.size}")
	private int pageSize = 0;

	public UserContext userContext = null;

	@ModelAttribute
	public void setUserContext() {
		userContext = UserContextHolder.getContext();
		if (userContext == null) {
			UserDTO dto = new UserDTO();
			dto.setLogin("krati@gmail.com"); 
			userContext = new UserContext(dto);
		}
	}
	
	
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
		
//		if (dto.getId() != null && dto.getId() > 0) {
//			System.out.println("before find by unique key");
//			T existsDTO = service.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
//			System.out.println(" data " + existsDTO);
//			if (existsDTO != null && existsDTO.getId() != dto.getId()) {
//				res.addMessage(dto.getLabel() + " already exists");
//				res.setSuccess(false);
//				return res;
//			}
//		}
		
//		else if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {
//			T existsDTO = service.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
//			if (existsDTO != null) {
//				res.addMessage(dto.getLabel() + " already exists while adding");
//				res.setSuccess(false);
//				return res;
//			}
//		}
		
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
	
	@RequestMapping(value = "search/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
	public ORSResponse search(@RequestBody F form, @PathVariable(required = false) int pageNo){
		
		ORSResponse res = new ORSResponse();
		
		T dto = form.getDto();
		pageNo = (pageNo < 0 ) ? 0 : pageNo;
		
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