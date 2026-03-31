package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.CollegeDTO;
import com.rays.form.CollegeForm;
import com.rays.service.CollegeServiceInt;

/**
 * REST controller for managing College-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link CollegeDTO}, {@link CollegeServiceInt},
 * and {@link CollegeForm}. All endpoints are accessible under the
 * {@code /College} base URL mapping.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "College")
public class CollegeCtl extends BaseCtl<CollegeDTO, CollegeServiceInt, CollegeForm> {
}