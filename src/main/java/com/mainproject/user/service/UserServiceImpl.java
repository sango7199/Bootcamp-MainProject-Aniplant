package com.mainproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mainproject.user.dao.UserDAO;
import com.mainproject.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override // 회원가입 로직
	public void registerUser(UserVO userVO) throws DataAccessException {
		userDAO.registerUser(userVO);
	}
	
	@Override // 회원가입 아이디 중복 검사 로직
	public boolean isIdDuplicate(String field, String value) {
		return userDAO.isDuplicate(field, value);
	}
	
	@Override //로그인 로직
	public UserVO loginUser(UserVO userVO) throws Exception{
		return userDAO.loginUser(userVO);
	}
	
	//로그인 로직 구현
	
	@Override
	public UserVO login(String id, String pwd) {
		UserVO user = userDAO.getUserById(id);
		
		if(user != null &&user.getPwd().equals(pwd)) {
			return user;
		}
		return null;
	}
	
	
}
