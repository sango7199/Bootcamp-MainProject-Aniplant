package com.mainproject.user.service;

import org.springframework.dao.DataAccessException;

import com.mainproject.user.vo.UserVO;

public interface UserService {
	public void registerUser(UserVO userVO) throws DataAccessException;
	public boolean isIdDuplicate(String field, String value);

	public UserVO loginUser(UserVO userVO) throws Exception;
	
	
	
	
	//로그인 로직구현
	public UserVO login(String id, String pwd);
	
}
