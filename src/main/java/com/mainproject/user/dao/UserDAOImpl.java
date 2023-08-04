package com.mainproject.user.dao;

import java.util.HashMap;
import java.util.Map;

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
	public boolean isDuplicate(String field, String value) throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
	    params.put("field", field);
	    params.put("value", value);
		int count = sqlSession.selectOne("mapper.user.checkIdDuplicate", params);
		return count > 0;
	}
}
