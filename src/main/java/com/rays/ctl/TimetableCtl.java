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
import com.rays.dto.TimetableDTO;
import com.rays.form.TimetableForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.SubjectServiceInt;
import com.rays.service.TimetableServiceInt;

/**
 * REST controller for managing Timetable-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link TimetableDTO}, {@link TimetableServiceInt},
 * and {@link TimetableForm}. All endpoints are accessible under the
 * {@code /Timetable} base URL mapping.
 * </p>
 * <p>
 * Additionally provides a preload endpoint to supply course and subject
 * dropdown data required by the Timetable form on the client side.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Timetable")
public class TimetableCtl extends BaseCtl<TimetableDTO, TimetableServiceInt, TimetableForm> {

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
     * Preloads dropdown data required for the Timetable form.
     * <p>
     * Retrieves and returns lists of all courses and subjects to be used
     * as dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the course and subject dropdown
     *         lists under the keys {@code courseList} and {@code subjectList}
     */
    @GetMapping("/preload")
    public ORSResponse preload() {
        ORSResponse res = new ORSResponse(true);

        List<DropdownList> courseList = courseService.search(new CourseDTO(), userContext);
        res.addResult("courseList", courseList);
        List<DropdownList> subjectList = subjectService.search(new SubjectDTO(), userContext);
        res.addResult("subjectList", subjectList);

        return res;
    }
}