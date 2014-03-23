package org.celts.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.celts.db.service.*;


@Controller
public class UserLoginController {
	
    //@Autowired //Here the authenticate service is automatically injected into this controller
    private UserAuthticateService userAuthenticateService;
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processCredentials(@RequestParam("uname")String uname,@RequestParam("pass")String pass) {
            String message = "Invalid credentials";
            if(userAuthenticateService.verifyUserNameAndPassword(uname, pass)) {
                    message = "Welcome " + uname + "!!";
            }
            return new ModelAndView("home","message",message);
    }

}
