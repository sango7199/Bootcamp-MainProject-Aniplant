package com.mainproject.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.user.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override // 회원가입 회원정보 등록 로직
	public int registerUser(UserVO userVO) throws DataAccessException {
		return sqlSession.insert("mapper.user.registerUser", userVO);
	}
	
	@Override // 회원가입 아이디 중복 검사 로직
	public boolean isIdDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkIdDuplicate", value);
		return count > 0;
	}
	
	@Override // 회원가입 닉네임 중복 검사 로직
	public boolean isNicknameDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkNicknameDuplicate", value);
		return count > 0;
	}
	
	@Override // 해당 username(ID)를 가진 사용자의 모든 정보 조회
    public UserVO getUserByUsername(String username) throws DataAccessException {
		return sqlSession.selectOne("mapper.user.getUserByUsername", username);
    }
}
