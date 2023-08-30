package com.mainproject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	// 사용자 메인 페이지 이동
	@RequestMapping(value = {"/index.do"}, method = RequestMethod.GET)
    public ModelAndView viewUserMain (HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
    }

	// 관리자 메인 페이지 이동
	@RequestMapping(value = {"/admin/main.do"}, method = RequestMethod.GET)
    public ModelAndView viewAdminMain (HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
    }

	// 세션 만료 페이지
	@GetMapping("/session-expired")
    public String sessionExpired() {
        return "sessionExpired";
    }
}
