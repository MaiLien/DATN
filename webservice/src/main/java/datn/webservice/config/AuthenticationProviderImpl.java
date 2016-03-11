package datn.webservice.config;

import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.constant.TypeOfUser;
import datn.interfaces.response.*;
import datn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationProviderImpl implements AuthenticationProvider, Serializable {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IOfficerService officerService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserResponse user = checkLogin(username, password);
        List<GrantedAuthority> grantedAuthorities = addGrantedAuthority(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        setAuthDetails(user, auth);

        return auth;
    }

    private UserResponse checkLogin(String username, String password){
        UserResponse user = userService.getUserByUsernameAndPassword(username, password);
        if (user == null)
            throw new BadCredentialsException(MessageCodeConstant.ERROR_USER_NOT_FOUND);
        return  user;
    }

    private List<GrantedAuthority> addGrantedAuthority(UserResponse user){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        ArrayList<RoleResponse> roles = roleService.getRolesByUserId(user.getId());
        for (int i = 0; i < roles.size(); i++) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roles.get(i).getName()));
        }

        return grantedAuthorities;
    }

    private void setAuthDetails(UserResponse user, Authentication auth){
        switch (user.getTypeOfUser()){
            case TypeOfUser.TEACHER:
                TeacherResponse teacher = teacherService.getTeacher(user.getId()).getBody();
                ((UsernamePasswordAuthenticationToken) auth).setDetails(teacher);
                break;
            case TypeOfUser.STUDENT:
                StudentResponse student = studentService.getStudent(user.getId()).getBody();
                ((UsernamePasswordAuthenticationToken) auth).setDetails(student);
                break;
            case TypeOfUser.OFFICER:
                OfficerResponse officer = officerService.getOfficer(user.getId()).getBody();
                ((UsernamePasswordAuthenticationToken) auth).setDetails(officer);
                break;
//            case ADMIN:

        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}