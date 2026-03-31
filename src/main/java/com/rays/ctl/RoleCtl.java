package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleServiceInt;

/**
 * REST controller for managing Role-related operations in the ORS application.
 * <p>
 * Extends {@link BaseCtl} to inherit common CRUD and search endpoints,
 * parameterized with {@link RoleDTO}, {@link RoleServiceInt},
 * and {@link RoleForm}. All endpoints are accessible under the
 * {@code /Role} base URL mapping.
 * </p>
 * <p>
 * Additionally provides a preload endpoint to supply role dropdown
 * data required by other forms on the client side.
 * </p>
 *
 * @author Krati Trivedi
 */
@RestController
@RequestMapping(value = "Role")
public class RoleCtl extends BaseCtl<RoleDTO, RoleServiceInt, RoleForm> {

    /**
     * Service for retrieving Role data used to populate the role dropdown.
     */
    @Autowired
    RoleServiceInt roleService;

    /**
     * Preloads dropdown data required for Role-dependent forms.
     * <p>
     * Retrieves and returns a list of all available roles to be used
     * as dropdown options on the client side.
     * </p>
     *
     * @return an {@link ORSResponse} containing the role dropdown list
     *         under the key {@code roleList}
     */
    @GetMapping(value = "preload")
    public ORSResponse preload() {
        ORSResponse res = new ORSResponse(true);
        RoleDTO dto = new RoleDTO();
        List<DropdownList> list = roleService.search(dto, userContext);
        res.addResult("roleList", list);

        return res;
    }
}