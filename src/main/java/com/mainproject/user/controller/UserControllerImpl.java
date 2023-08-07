package com.mainproject.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.user.service.UserDetailsServiceImpl;
import com.mainproject.user.service.UserService;
import com.mainproject.user.vo.LoginRequest;
import com.mainproject.user.vo.UserVO;

@Controller("userController")
public class UserControllerImpl implements UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
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
	public ResponseEntity<Map<String, Boolean>> checkId(@RequestParam("id") String id) {
		boolean isDuplicate = userService.isIdDuplicate(id);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("isDuplicate", isDuplicate);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override // 닉네임 중복 체크 로직
	@PostMapping("/api/check-nickname")
	public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam("nickname") String nickname) {
		 boolean isDuplicate = userService.isNicknameDuplicate(nickname);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("isDuplicate", isDuplicate);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override // 회원가입 완료 페이지 이동
	@RequestMapping(value = {"/user/join-complete.do"}, method = RequestMethod.GET)
	public ModelAndView viewJoinComplete(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("name", name);
		return mav;
	}
	
	@Override // 로그인 페이지 이동
	@RequestMapping(value = {"/user/login.do"}, method = RequestMethod.GET)
	public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override // 로그인 로직
	@PostMapping("/api/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
	    String username = loginRequest.getId();
	    String password = loginRequest.getPwd();
	    
	    // 사용자의 입력을 검증하고 인증된 사용자 정보를 생성
	    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
	    if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 잘못되었습니다.");
	    }

	    // 인증된 사용자 정보를 세션에 저장
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

	    return ResponseEntity.ok().body("로그인에 성공하였습니다.");
	}
	
	@Override // 로그아웃 로직
	@GetMapping("/api/logout")
	public ResponseEntity<?> logout(HttpSession session) {
	    // 세션에서 사용자 정보를 제거
	    session.removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	    SecurityContextHolder.clearContext();

	    return ResponseEntity.ok().body("로그아웃에 성공하였습니다.");
	}
	

}
