package com.mainproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/index")
    public ModelAndView main() {
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("pages/index");
        return mav;
    }

}