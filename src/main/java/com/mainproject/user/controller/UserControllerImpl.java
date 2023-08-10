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
	
	@Override // 사용자가입 페이지 이동
	@RequestMapping(value = {"/user/join.do"}, method = RequestMethod.GET)
	public ModelAndView viewJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override // 사용자 등록 입력
	@PostMapping("/api/register")
	public ResponseEntity<String> registerUser(@RequestBody UserVO userVO) {
	    try {
	        userService.registerUser(userVO);
	        return new ResponseEntity<>("success", HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@Override // 아이디 중복 확인 입력
	@PostMapping("/api/check-id")
	public ResponseEntity<Map<String, Boolean>> checkId(@RequestParam("id") String id) {
		boolean isDuplicate = userService.isIdDuplicate(id);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("isDuplicate", isDuplicate);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override // 정보 입력 중복 확인 입력
	@PostMapping("/api/check-nickname")
	public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam("nickname") String nickname) {
		 boolean isDuplicate = userService.isNicknameDuplicate(nickname);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("isDuplicate", isDuplicate);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override // 사용자가입 확인 페이지 이동
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
	
	@Override // 로그인 입력
	@PostMapping("/api/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session, HttpServletRequest request) {
	    String username = loginRequest.getId();
	    String password = loginRequest.getPwd();
	    
	    // 사용자의 입력을 받아 확인된 사용자 정보를 생성
	    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
	    if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("�븘�씠�뵒 �삉�뒗 鍮꾨�踰덊샇媛� �옒紐삳릺�뿀�뒿�땲�떎.");
	    }

	    // 확인된 사용자 정보를 필요한 곳에 저장
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

	    // 로그인 성공 후 redirectUrl 생성
	    String referer = (String) session.getAttribute("previousPage");
	    if (referer == null || referer.isBlank()) {
	        referer = "/index.do";  // 기본 URL 설정
	    }

	    Map<String, String> result = new HashMap<>();
	    result.put("redirectUrl", referer);

	    return ResponseEntity.ok(result);
	}

	
	@Override // 로그아웃 입력
	@GetMapping("/api/logout")
	public ResponseEntity<?> logout(HttpSession session) {
	    // 필요한 곳에서 사용자 정보 삭제
	    session.removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	    SecurityContextHolder.clearContext();

	    return ResponseEntity.ok().body("濡쒓렇�븘�썐�뿉 �꽦怨듯븯���뒿�땲�떎.");
	}
	
	@Override // 회원정보 설정 메뉴 페이지 이동
	@RequestMapping(value = {"/admin/test.do"}, method = RequestMethod.GET)
	public ModelAndView viewAdminTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

}
