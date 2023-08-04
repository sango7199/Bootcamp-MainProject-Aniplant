package com.mainproject.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.user.service.UserService;
import com.mainproject.user.vo.UserVO;

@Controller("userController")
public class UserControllerImpl implements UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	UserVO userVO;
	
	@Override // 회원가입 페이지 이동
	@RequestMapping(value = {"/user/join.do"}, method = RequestMethod.GET)
	public ModelAndView viewJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override // 회원가입 로직
	@PostMapping("/api/register")
	public ResponseEntity<String> registerUser(@RequestBody UserVO userVO) {
	    try {
	        userService.registerUser(userVO);
	        return new ResponseEntity<>("success", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@Override // 아이디 중복 체크 로직
	@PostMapping("/api/check-id")
	public ResponseEntity<Boolean> checkId(@RequestParam("id") String id) {
		 boolean isDuplicate = userService.isIdDuplicate("ID", id);
		 return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
	}
	
	@Override // 닉네임 중복 체크 로직
	@PostMapping("/api/check-nickname")
	public ResponseEntity<Boolean> checkNickname(@RequestParam("nickname") String nickname) {
		 boolean isDuplicate = userService.isIdDuplicate("Nickname",nickname);
		 return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
	}
	
	@Override // 로그인 페이지 이동
	@RequestMapping(value = {"/user/login.do"}, method = RequestMethod.GET)
	public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
		
	}
	

	
	//로그인 로직 구현
	@PostMapping("/api/login")
	@ResponseBody
	public String login(@RequestParam String id, @RequestParam String pwd) {
		UserVO user = userService.login(id, pwd);
		
		if(user != null) {
			return "success";
		}
		return "failure";
	}
}
