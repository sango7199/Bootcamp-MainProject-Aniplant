package com.mainproject;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.pet.service.PetService;
import com.mainproject.pet.vo.PetVO;
import com.mainproject.user.service.UserService;
import com.mainproject.user.vo.UserVO;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PetService petService;

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
	
	// 마이페이지 - 메인 페이지 이동
	@GetMapping("/mypage/main.do")
	public ModelAndView viewMypageMain (HttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		// 회원 정보 불러오기
		String userID = principal.getName();
		System.out.println(userID);
		UserVO userInfo = userService.getUserByUsername(userID);
		int userNum = userInfo.getUser_num();
		System.out.println(userInfo);
		// 반려친구 정보 불러오기
		List<PetVO> petInfo = petService.getAllPet(userNum);
		System.out.println(petInfo);
		// 회원 일정 불러오기 
		// 코드 추가 필요
		mav.setViewName(viewName);
		mav.addObject("user", userInfo);
		mav.addObject("pet", petInfo);
		return mav;
	}  
}
