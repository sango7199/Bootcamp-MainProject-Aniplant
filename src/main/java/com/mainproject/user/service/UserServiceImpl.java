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

	@Override // 로그인 실패 체크 로직
	public void increaseLoginFailCount(String username) throws DataAccessException {
		userDAO.increaseLoginFailCount(username);
	}
	
	@Override // 로그인 성공 시 fail_count 초기화 로직
	public void resetLoginFailCount(String username) throws DataAccessException {
		userDAO.resetLoginFailCount(username);
	}
	
	
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
	
	@Override // 회원 ID로 회원정보 조회 로직
	public UserVO getUserByUsername(String username) throws DataAccessException {
		return userDAO.getUserByUsername(username);
	}
	
	@Override // 회원 정보 수정 로직
	public void updateUser(UserVO userVO) throws DataAccessException {
		// 비밀번호 변경점으로 분기 처리
		if (userVO.getPwd() != null && !userVO.getPwd().isEmpty()) { // 변경했다면
	        String encodedPassword = passwordEncoder.encode(userVO.getPwd());
	        userVO.setPwd(encodedPassword);
	        userDAO.updateUserWithPassword(userVO);
	    } else { // 변경하지 않았다면
	        userDAO.updateUserWithoutPassword(userVO);
	    }
	}
	
	@Override // 회원 탈퇴 로직
	public void deleteUser(UserVO userVO) throws DataAccessException {
		userDAO.deleteUser(userVO);
	}
}
