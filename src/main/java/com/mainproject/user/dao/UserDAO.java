package com.mainproject.user.dao;

import org.springframework.dao.DataAccessException;

import com.mainproject.user.vo.UserVO;

public interface UserDAO {
	public int registerUser(UserVO userVO) throws DataAccessException;
	public boolean isIdDuplicate(String value) throws DataAccessException;
	public boolean isNicknameDuplicate(String value) throws DataAccessException;
	public UserVO getUserByUsername(String username) throws DataAccessException;
	public void updateUserWithPassword(UserVO userVO) throws DataAccessException;
	public void updateUserWithoutPassword(UserVO userVO) throws DataAccessException;
	public void deleteUser(UserVO userVO) throws DataAccessException;
	public void increaseLoginFailCount(String username);
	public void resetLoginFailCount(String username);
}
