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
	
	@Override
	public void registerUser(UserVO userVO) throws DataAccessException {
		userDAO.registerUser(userVO);
	}
}
