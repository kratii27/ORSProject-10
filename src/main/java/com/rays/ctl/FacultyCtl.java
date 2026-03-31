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
import com.rays.dto.CollegeDTO;
import com.rays.dto.CourseDTO;
import com.rays.dto.FacultyDTO;
import com.rays.dto.SubjectDTO;
import com.rays.form.FacultyForm;
import com.rays.service.CollegeServiceInt;
import com.rays.service.CourseServiceInt;
import com.rays.service.FacultyServiceInt;
import com.rays.service.SubjectServiceInt;

/**
 * REST controller for managing Faculty-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link FacultyDTO}, {@link FacultyServiceInt},
 * and {@link FacultyForm}. All endpoints are accessible under the
 * {@code /Faculty} base URL mapping.
 * </p>
 * <p>
 * Additionally provides a preload endpoint to supply dropdown data
 * for courses, subjects, and colleges required by the Faculty form.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Faculty")
public class FacultyCtl extends BaseCtl<FacultyDTO, FacultyServiceInt, FacultyForm> {

    /**
     * Service for retrieving Course data used to populate the course dropdown.
     */
    @Autowired
    private CourseServiceInt courseService;

    /**
     * Service for retrieving Subject data used to populate the subject dropdown.
     */
    @Autowired
    private SubjectServiceInt subjectService;

    /**
     * Service for retrieving College data used to populate the college dropdown.
     */
    @Autowired
    private CollegeServiceInt collegeService;

    /**
     * Preloads dropdown data required for the Faculty form.
     * <p>
     * Retrieves and returns lists of courses, subjects, and colleges
     * to be used as dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the course, subject,
     *         and college dropdown lists under the keys
     *         {@code courseList}, {@code subjectList}, and {@code collegeList}
     */
    @GetMapping("/preload")
    public ORSResponse preload() {
        ORSResponse res = new ORSResponse(true);

        List<DropdownList> courseList = courseService.search(new CourseDTO(), userContext);
        List<DropdownList> subjectList = subjectService.search(new SubjectDTO(), userContext);
        List<DropdownList> collegeList = collegeService.search(new CollegeDTO(), userContext);

        res.addResult("courseList", courseList);
        res.addResult("subjectList", subjectList);
        res.addResult("collegeList", collegeList);

        return res;
    }
}