package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;
import com.rays.form.MarksheetForm;
import com.rays.service.MarksheetServiceInt;
import com.rays.service.StudentServiceInt;

/**
 * REST controller for managing Marksheet-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link MarksheetDTO}, {@link MarksheetServiceInt},
 * and {@link MarksheetForm}. All endpoints are accessible under the
 * {@code /Marksheet} base URL mapping.
 * </p>
 * <p>
 * Additionally provides a preload endpoint to supply student dropdown
 * data required by the Marksheet form on the client side.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Marksheet")
public class MarksheetCtl extends BaseCtl<MarksheetDTO, MarksheetServiceInt, MarksheetForm> {

    /**
     * Service for retrieving Student data used to populate the student dropdown.
     */
    @Autowired
    public StudentServiceInt studentService;

    /**
     * Preloads dropdown data required for the Marksheet form.
     * <p>
     * Retrieves and returns a list of all students to be used as
     * dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the student dropdown list
     *         under the key {@code studentList}
     */
    @GetMapping("/preload")
    public ORSResponse preload() {

        ORSResponse res = new ORSResponse(true);

        List<DropdownList> studentList = studentService.search(new StudentDTO(), userContext);

        res.addResult("studentList", studentList);

        return res;
    }
    
    @GetMapping("rollno/{rollNo}")
	public ORSResponse rollNo(@PathVariable String rollNo) {
		ORSResponse res = new ORSResponse(true);
		MarksheetDTO dto = service.findByRollNo(rollNo, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@GetMapping("meritlist")
	public ORSResponse getMeritList() {
		System.out.println("getMeritList run on ctl");
		List<MarksheetDTO> list = service.getMeritList(userContext);
		ORSResponse res = new ORSResponse(true);
		res.addResult("list",list);
//		System.out.println("Merit list :: " + list);
		return res;
	}
}