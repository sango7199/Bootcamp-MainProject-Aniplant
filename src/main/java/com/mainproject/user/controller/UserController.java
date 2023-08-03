package com.mainproject.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.user.vo.UserVO;

public interface UserController {
	public ModelAndView viewJoin(HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ResponseEntity<?> registerUser(@RequestBody UserVO userVO);
}
