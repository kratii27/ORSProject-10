package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.dto.CourseDTO;
import com.rays.dto.FeeStructureDTO;
import com.rays.form.FeeStructureForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.FeeStructureServiceInt;

@RestController
@RequestMapping(value = "FeeStructure")
public class FeeStructureCtl extends BaseCtl<FeeStructureDTO, FeeStructureServiceInt, FeeStructureForm> {
	
	@Autowired
	private CourseServiceInt courseService;
	
	
	
	@GetMapping(value = "preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		CourseDTO dto = new CourseDTO();
		List<DropdownList> courseList = courseService.search(dto, userContext);
		res.addResult("courseList", courseList);
		return res;
	}
}