package com.mainproject.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.user.vo.LoginRequest;
import com.mainproject.user.vo.UserVO;

public interface UserController {
	public ModelAndView viewJoin(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> registerUser(@RequestBody UserVO userVO);
	public ResponseEntity<Map<String, Boolean>> checkId(@RequestParam("id") String id);
	public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam("nickname") String nickname);
	public ModelAndView viewJoinComplete(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session, HttpServletRequest request);
	public ResponseEntity<?> logout(HttpSession session);
	public ModelAndView viewAdminTest(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
