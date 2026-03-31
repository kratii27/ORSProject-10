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

/**
 * REST controller for managing Subject-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link SubjectDTO}, {@link SubjectServiceInt},
 * and {@link SubjectForm}. All endpoints are accessible under the
 * {@code /Subject} base URL mapping.
 * </p>
 * <p>
 * Additionally provides a preload endpoint to supply course dropdown
 * data required by the Subject form on the client side.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Subject")
public class SubjectCtl extends BaseCtl<SubjectDTO, SubjectServiceInt, SubjectForm> {

    /**
     * Service for retrieving Course data used to populate the course dropdown.
     */
    @Autowired
    private CourseServiceInt courseService;

    /**
     * Preloads dropdown data required for the Subject form.
     * <p>
     * Retrieves and returns a list of all courses to be used as
     * dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the course dropdown list
     *         under the key {@code courseList}
     */
    @GetMapping("/preload")
    public ORSResponse preload() {

        ORSResponse res = new ORSResponse(true);

        List<DropdownList> courseList = courseService.search(new CourseDTO(), userContext);

        res.addResult("courseList", courseList);

        return res;
    }
}