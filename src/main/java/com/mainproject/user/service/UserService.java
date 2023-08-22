package com.mainproject.user.service;

import java.security.Principal;
import java.util.List;

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
	public List<UserVO> getAllUsers() throws DataAccessException;
	public UserVO getUserByUserNum(int user_num) throws DataAccessException;
	public void updateUserDetail(UserVO userVO, int curUserNum) throws DataAccessException;
	public String suspendUser(int userNum, String action) throws Exception;
	public void removeUser(int user_num) throws Exception;
}
