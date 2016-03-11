package datn.webservice.controller;

import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RoleResponse;
import datn.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<RoleResponse>> getRoles(String id){
        return new RestApiResponse<ArrayList<RoleResponse>>(roleService.getRolesByUserId(id));
    }

}
