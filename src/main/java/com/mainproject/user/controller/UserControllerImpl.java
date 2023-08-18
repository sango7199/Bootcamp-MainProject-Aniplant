package com.mainproject.user.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import com.mainproject.user.dao.UserDAO;
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
	@PostMapping("/api/register-user")
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
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session, HttpServletRequest request) {
		Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(currentAuthentication instanceof AnonymousAuthenticationToken)) {
	        // 이미 로그인된 상태라면 원하는 메시지나 리다이렉트 URL을 반환
	        Map<String, String> result = new HashMap<>();
	        result.put("message", "이미 로그인된 상태입니다.");
	        return ResponseEntity.ok(result);
	    } else { 
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
	
		    // 로그인 성공 후 redirectUrl 생성
		    String referer = (String) session.getAttribute("previousPage");
		    if (referer == null || referer.isBlank()) {
		        referer = "/index.do";  // 기본 URL 설정
		    }
		    
		    Map<String, String> result = new HashMap<>();
		    result.put("redirectUrl", referer);
		    
		    return ResponseEntity.ok(result); 
	    }
	}

	
	@Override // 로그아웃 로직
	@GetMapping("/api/logout")
	public ResponseEntity<?> logout(HttpSession session) {
	    // 세션에서 사용자 정보를 제거
	    session.removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	    SecurityContextHolder.clearContext();

	    return ResponseEntity.ok().body("로그아웃에 성공하였습니다.");
	}
	
	@Override // 권한 설정 테스트 페이지 이동
	@RequestMapping(value = {"/admin/test.do"}, method = RequestMethod.GET)
	public ModelAndView viewAdminTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override // 마이페이지 하위 GET 요청에 대한 모든 페이지 이동
	@RequestMapping(value = {"/mypage/**.do"}, method = RequestMethod.GET)
	public ModelAndView viewMyprofile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override // 회원정보 관리 비밀번호 확인 로직
	@PostMapping("/api/confirmUser")
	public ResponseEntity<?> confirmPWD(@RequestParam("pwd") String pwd, Principal principal) {
	    // 현재 로그인된 사용자 정보 가져오기
	    String currentUsername = principal.getName();
	    
	    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(currentUsername);

	    if (passwordEncoder.matches(pwd, userDetails.getPassword())) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "success");
	        response.put("isAuthenticated", true); // 현재 사용자가 인증된 상태
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("errorMessage", "비밀번호가 잘못되었습니다.");
	        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	    }
	}
	
	@Override // 회원정보 수정하기 form 페이지 로드 로직
	@RequestMapping(value = {"/mypage/my-info-update.do"}, method = RequestMethod.GET)
	public ModelAndView viewMyInfoUpdate(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		String username = principal.getName();
		UserVO user = userService.getUserByUsername(username);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("user", user);
		return mav;
	}
	
	@Override // 회원정보 수정 로직
	@PostMapping("/api/update-user")
	public ResponseEntity<?> updateUser(@RequestBody UserVO userVO, Principal principal) {
	    String currentUsername = principal.getName();
//	    System.out.println(currentUsername);
	    // 현재 로그인된 사용자의 정보를 가져옴
	    UserVO currentUser = userService.getUserByUsername(currentUsername);
//	    System.out.println(currentUser);

//	    // 현재 로그인된 사용자의 정보만 수정 가능하도록 체크
//	    if (!currentUser.getId().equals(userVO.getId())) {
//	        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
//	    }

	    try {
	        userService.updateUser(userVO);
	        return new ResponseEntity<>("success", HttpStatus.OK);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@Override // 반려친구 등록페이지
	@RequestMapping(value = {"/user/pet-join.do"}, method = RequestMethod.GET)
	public ModelAndView viewPetJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
}
