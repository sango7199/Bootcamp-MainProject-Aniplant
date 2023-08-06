package com.mainproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mainproject.user.dao.UserDAO;
import com.mainproject.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override // 회원가입 로직
	public void registerUser(UserVO userVO) throws DataAccessException {
		String encodedPassword = passwordEncoder.encode(userVO.getPwd()); // 비밀번호 암호화
		userVO.setPwd(encodedPassword);
		userDAO.registerUser(userVO);
	}
	
	@Override // 회원가입 아이디 중복 검사 로직
	public boolean isIdDuplicate(String value) {
		return userDAO.isIdDuplicate(value);
	}
	
	@Override // 회원가입 닉네임 중복 검사 로직
	public boolean isNicknameDuplicate(String value) {
		return userDAO.isNicknameDuplicate(value);
	}
	
	// 로그인 로직
	
}
