package com.mainproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class LoginController {
	  @GetMapping("/login")
	    public ModelAndView main() {
	    	ModelAndView mav = new ModelAndView();
	        mav.setViewName("user/login");
	        return mav;
	    }
}
