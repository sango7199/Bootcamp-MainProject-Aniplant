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
	
	@Override // ����� ��� ����� ���� �Է� �ޱ�
	public int registerUser(UserVO userVO) throws DataAccessException {
		return sqlSession.insert("mapper.user.registerUser", userVO);
	}
	
	@Override // ����� ���� ���̵� �ߺ� Ȯ�� �Է�
	public boolean isIdDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkIdDuplicate", value);
		return count > 0;
	}
	
	@Override // ����� ���� ���� �Է� �ߺ� Ȯ��
	public boolean isNicknameDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkNicknameDuplicate", value);
		return count > 0;
	}
	
	@Override //Ư�� username(ID)�� �Է��� ������� ��� ���� ��ȸ
    public UserVO getUserByUsername(String username) throws DataAccessException {
		return sqlSession.selectOne("mapper.user.getUserByUsername", username);
    }
	
	@Override // 회원 정보 수정 로직 (비밀번호 변경)
	public void updateUserWithPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithPassword",userVO);
	}
	
	@Override // 회원 정보 수정 로직 (비밀번호 미변경)
	public void updateUserWithoutPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithoutPassword",userVO);
	}
}
