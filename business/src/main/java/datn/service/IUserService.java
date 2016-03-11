package datn.service;

import datn.interfaces.response.UserResponse;

public interface IUserService {

    public UserResponse getUserByUsernameAndPassword(String username, String password);

//    public UserSecurity getUserDetails(String username);

}
