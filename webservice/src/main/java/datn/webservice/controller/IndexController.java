package datn.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String getIndexPage(){
        return "index";
    }

//    @RequestMapping(value = "/API/login", method = RequestMethod.GET)
//    public ModelAndView loginPage(HttpServletRequest request){
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "LOGIN PAGE");
//        model.setViewName("login");
//        return model;
//    }

}
