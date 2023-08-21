package com.mainproject.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.user.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override // 로그인 실패 체크 로직
	public void increaseLoginFailCount(String username) {
		sqlSession.update("mapper.user.increaseLoginFailCount",username);
	}
	
	@Override // 로그인 성공 시 fail_count 초기화 로직
	public void resetLoginFailCount(String username) {
		sqlSession.update("mapper.user.resetLoginFailCount",username);
	}

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
	
	@Override // 회원 정보 수정 로직 (비밀번호 변경)
	public void updateUserWithPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithPassword",userVO);
	}
	
	@Override // 회원 정보 수정 로직 (비밀번호 미변경)
	public void updateUserWithoutPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithoutPassword",userVO);
	}
	
	@Override // 회원 탈퇴 로직
	public void deleteUser(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.deleteUser", userVO);
	}
	
	// 관리자 영역
	@Override // 모든 회원 정보 가져오는 로직
	public List<UserVO> getAllUsers() throws DataAccessException {
		return sqlSession.selectList("mapper.user.getAllUsers");
	}
	
	@Override // 회원 번호로 유저 정보 가져오는 로직
	public UserVO getUserByUserNum(int user_num) throws DataAccessException { 
		return sqlSession.selectOne("mapper.user.getUserByUserNum", user_num);
	}
	
	@Override // 회원 상세 정보수정 로직
	public void updateUserDetail(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserDetail", userVO);
	}

	@Override // 회원 정지 로직
    public void suspendUser(int userNum) throws DataAccessException {
        sqlSession.update("mapper.user.suspendUser", userNum);
    }

    @Override // 회원 정지 해제 로직
    public void unsuspendUser(int userNum) throws DataAccessException {
        sqlSession.update("mapper.user.unsuspendUser", userNum);
    }

	@Override // 회원 정보 삭제 로직
	public void removeUser(int user_num) throws DataAccessException {
		sqlSession.delete("mapper.user.removeUser", user_num);
	}
}
