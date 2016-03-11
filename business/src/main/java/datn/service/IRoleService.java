package datn.service;

import datn.interfaces.response.RoleResponse;

import java.util.ArrayList;

public interface IRoleService {

    ArrayList<RoleResponse> getRolesByUserId(String userId);

}
