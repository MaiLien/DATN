package datn.controller;

import datn.entity.Officer;
import datn.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    OfficerRepository officerRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPageS(HttpServletRequest request){
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String loginPages(HttpServletRequest request){
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPageas(HttpServletRequest request){
        Officer obj = new Officer();
        obj.setId(UUID.randomUUID().toString());

        officerRepository.save(obj);

        return "index";
    }

}
