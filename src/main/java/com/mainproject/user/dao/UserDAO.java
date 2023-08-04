package com.mainproject.user.dao;

import org.springframework.dao.DataAccessException;

import com.mainproject.user.vo.UserVO;

public interface UserDAO {
	public int registerUser(UserVO userVO) throws DataAccessException;
	public boolean isDuplicate(String field, String id) throws DataAccessException;
}
