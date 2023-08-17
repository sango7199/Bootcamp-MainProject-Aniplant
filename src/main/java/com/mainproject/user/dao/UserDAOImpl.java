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
	
<<<<<<< HEAD
	@Override // 사용자 등록 사용자 정보 입력 받기
=======
	@Override // 로그인 실패 체크 로직
	public void increaseLoginFailCount(String username) {
		sqlSession.update("mapper.user.increaseLoginFailCount",username);
	}
	
	@Override // 로그인 성공 시 fail_count 초기화 로직
	public void resetLoginFailCount(String username) {
		sqlSession.update("mapper.user.resetLoginFailCount",username);
	}

	@Override // 회원가입 회원정보 등록 로직
>>>>>>> 7ee07e5f36170e20126f456ca5d2693fe4571b6a
	public int registerUser(UserVO userVO) throws DataAccessException {
		return sqlSession.insert("mapper.user.registerUser", userVO);
	}
	
	@Override // 사용자 가입 아이디 중복 확인 입력
	public boolean isIdDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkIdDuplicate", value);
		return count > 0;
	}
	
	@Override // 사용자 가입 정보 입력 중복 확인
	public boolean isNicknameDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkNicknameDuplicate", value);
		return count > 0;
	}  
	
	@Override //특정 username(ID)을 입력한 사용자의 모든 정보 조회
    public UserVO getUserByUsername(String username) throws DataAccessException {
		return sqlSession.selectOne("mapper.user.getUserByUsername", username);
    }
	
	@Override // �쉶�썝 �젙蹂� �닔�젙 濡쒖쭅 (鍮꾨�踰덊샇 蹂�寃�)
	public void updateUserWithPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithPassword",userVO);
	}
	
	@Override // �쉶�썝 �젙蹂� �닔�젙 濡쒖쭅 (鍮꾨�踰덊샇 誘몃�寃�)
	public void updateUserWithoutPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithoutPassword",userVO);
	}
	
	@Override // 회원 탈퇴 로직
	public void deleteUser(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.deleteUser", userVO);
	}
}
