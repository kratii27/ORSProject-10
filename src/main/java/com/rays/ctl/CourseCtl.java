package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.CourseDTO;
import com.rays.form.CourseForm;
import com.rays.service.CourseServiceInt;

/**
 * REST controller for managing Course-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link CourseDTO}, {@link CourseServiceInt},
 * and {@link CourseForm}. All endpoints are accessible under the
 * {@code /Course} base URL mapping.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Course")
public class CourseCtl extends BaseCtl<CourseDTO, CourseServiceInt, CourseForm> {
}