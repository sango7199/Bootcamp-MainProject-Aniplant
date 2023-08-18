package com.mainproject.user.service;

import org.springframework.dao.DataAccessException;

import com.mainproject.user.vo.UserVO;

public interface UserService {
    public void registerUser(UserVO userVO) throws DataAccessException;
    public boolean isIdDuplicate(String value);
    public boolean isNicknameDuplicate(String value);
    public UserVO getUserByUsername(String username) throws DataAccessException;
    public void updateUser(UserVO userVO) throws DataAccessException;
	public void deleteUser(UserVO userVO) throws DataAccessException;
	public void increaseLoginFailCount(String username) throws DataAccessException;
	public void resetLoginFailCount(String username) throws DataAccessException;
}
