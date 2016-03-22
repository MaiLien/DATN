package datn.service.impl;

import datn.dao.entity.User;
import datn.dao.repository.UserRepository;
import datn.interfaces.response.UserResponse;
import datn.interfaces.util.DateFormatUtil;
import datn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserByUsernameAndPassword(String username, String password) {
        User userEntity = userRepository.findUserByUsername(username);
        UserResponse userResponse = null;
        if(userEntity != null) {
            if(matchPassword(password, userEntity.getPassword())){
                userResponse = converUserEntityToUserResponse(userEntity);
            }
        }

        return userResponse;
    }

    private boolean matchPassword(String plainPass, String hashPass){
        return BCrypt.checkpw(plainPass,hashPass);
    }

    private UserResponse converUserEntityToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setBirthday(DateFormatUtil.convertDateToString(user.getBirthday()));
        userResponse.setDeleted(user.getDeleted());
        userResponse.setDescription(user.getDescription());
        userResponse.setEmail(user.getEmail());
        userResponse.setGender(user.getGender().getValue());
        userResponse.setName(user.getName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setStatus(user.getStatus());
        userResponse.setTypeOfUser(user.getTypeOfUser().getValue());

        return userResponse;
    }

//    public UserSecurity getUserDetails(String username) {
//        User user = userRepository.findByUsername(username);
//        UserAuth userAuth = converUserEntityToUserAuth(user);
//        return new UserSecurity(userAuth);
//    }

//    private UserAuth converUserEntityToUserAuth(User user) {
//        UserAuth userAuth = new UserAuth();
//        BeanUtils.copyProperties(user, userAuth);
//
//        return userAuth;
//    }

}
