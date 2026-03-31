package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.dto.StudentDTO;
import com.rays.form.StudentForm;
import com.rays.service.CollegeServiceInt;
import com.rays.service.StudentServiceInt;

/**
 * REST controller for managing Student-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link StudentDTO}, {@link StudentServiceInt},
 * and {@link StudentForm}. All endpoints are accessible under the
 * {@code /Student} base URL mapping.
 * </p>
 * <p>
 * Additionally provides a preload endpoint to supply college dropdown
 * data required by the Student form on the client side.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Student")
public class StudentCtl extends BaseCtl<StudentDTO, StudentServiceInt, StudentForm> {

    /**
     * Service for retrieving College data used to populate the college dropdown.
     */
    @Autowired
    public CollegeServiceInt collegeService;

    /**
     * Preloads dropdown data required for the Student form.
     * <p>
     * Retrieves and returns a list of all colleges to be used as
     * dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the college dropdown list
     *         under the key {@code collegeList}
     */
    @GetMapping("/preload")
    public ORSResponse preload() {

        ORSResponse res = new ORSResponse(true);

        List<DropdownList> collegeList = collegeService.search(new CollegeDTO(), userContext);

        res.addResult("collegeList", collegeList);

        return res;
    }
}