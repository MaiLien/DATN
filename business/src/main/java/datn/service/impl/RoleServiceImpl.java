package datn.service.impl;

import datn.dao.entity.Role;
import datn.dao.repository.UserRoleRepository;
import datn.interfaces.response.RoleResponse;
import datn.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class RoleServiceImpl implements IRoleService{

//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public ArrayList<RoleResponse> getRolesByUserId(String userId) {
        ArrayList<Role> roles = userRoleRepository.getRolesByUserId(userId);
        ArrayList<RoleResponse> roleResponses = new ArrayList<RoleResponse>();
        RoleResponse roleResponse;
        for (int i = 0; i < roles.size(); i++) {
            roleResponse = convertRoleEntityToRoleResponse(roles.get(i));
            roleResponses.add(roleResponse);
        }

        return roleResponses;
    }


    private RoleResponse convertRoleEntityToRoleResponse(Role role){
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());

        return roleResponse;
    }
}
