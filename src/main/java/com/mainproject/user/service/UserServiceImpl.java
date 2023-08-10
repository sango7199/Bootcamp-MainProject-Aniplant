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
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	@Override // 사용자 등록 입력
	public void registerUser(UserVO userVO) throws DataAccessException {
		String encodedPassword = passwordEncoder.encode(userVO.getPwd()); // 鍮꾨�踰덊샇 �븫�샇�솕
		userVO.setPwd(encodedPassword);
		userDAO.registerUser(userVO);
	}
	
	@Override // 사용자 가입 아이디 중복 확인 입력
	public boolean isIdDuplicate(String value) {
		return userDAO.isIdDuplicate(value);
	}
	
	@Override // 사용자 가입 정보 입력 중복 확인
	public boolean isNicknameDuplicate(String value) {
		return userDAO.isNicknameDuplicate(value);
	}
}
