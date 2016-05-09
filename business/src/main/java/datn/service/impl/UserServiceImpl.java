package datn.service.impl;

import datn.dao.entity.User;
import datn.dao.repository.UserRepository;
import datn.interfaces.response.UserResponse;
import datn.interfaces.util.DateUtil;
import datn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserByUsernameAndPassword(String username, String password) {
        User userEntity = userRepository.findUserByUsername(username);
        UserResponse userResponse = null;
        if (userEntity != null) {
            if (matchPassword(password, userEntity.getPassword())) {
                userResponse = converUserEntityToUserResponse(userEntity);
            }
        }

        return userResponse;
    }

    private boolean matchPassword(String plainPass, String hashPass) {
        if (plainPass == null || hashPass == null) {
            return false;
        } else {
            return BCrypt.checkpw(plainPass, hashPass);
        }
    }

    public  static void main(String[] args){
        System.out.println(BCrypt.hashpw("102110134", BCrypt.gensalt(12)));
    }

    private UserResponse converUserEntityToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setBirthday(DateUtil.convertDateToString(user.getBirthday()));
        userResponse.setDescription(user.getDescription());
        userResponse.setEmail(user.getEmail());
        userResponse.setGender(user.getGender().getValue());
        userResponse.setName(user.getName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setStatus(user.getStatus());
        userResponse.setTypeOfUser(user.getTypeOfUser().getValue());

        return userResponse;
    }

}
