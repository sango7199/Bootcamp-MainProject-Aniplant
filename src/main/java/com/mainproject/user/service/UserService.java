package com.mainproject.user.service;

import org.springframework.dao.DataAccessException;

import com.mainproject.user.vo.UserVO;

public interface UserService {
	public void registerUser(UserVO userVO) throws DataAccessException;
	public boolean isIdDuplicate(String field, String value);
}
