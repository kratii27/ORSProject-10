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
import com.rays.dto.SubjectDTO;
import com.rays.form.SubjectForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.SubjectServiceInt;

@RestController
@RequestMapping(value = "Subject")
public class SubjectCtl extends BaseCtl<SubjectDTO, SubjectServiceInt, SubjectForm> {
	
	@Autowired
	private CourseServiceInt courseService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		
		ORSResponse res = new ORSResponse(true);
		
		List<DropdownList> courseList = courseService.search(new CourseDTO(), userContext);
		
		res.addResult("courseList", courseList);
		
		return res;
		
	}

}
