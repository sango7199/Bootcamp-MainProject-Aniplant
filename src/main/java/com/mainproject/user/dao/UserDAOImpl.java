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
	
	@Override // »ç¿ëÀÚ µî·Ï »ç¿ëÀÚ Á¤º¸ ÀÔ·Â ¹Ş±â
	public int registerUser(UserVO userVO) throws DataAccessException {
		return sqlSession.insert("mapper.user.registerUser", userVO);
	}
	
	@Override // »ç¿ëÀÚ °¡ÀÔ ¾ÆÀÌµğ Áßº¹ È®ÀÎ ÀÔ·Â
	public boolean isIdDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkIdDuplicate", value);
		return count > 0;
	}
	
	@Override // »ç¿ëÀÚ °¡ÀÔ Á¤º¸ ÀÔ·Â Áßº¹ È®ÀÎ
	public boolean isNicknameDuplicate(String value) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.user.checkNicknameDuplicate", value);
		return count > 0;
	}
	
	@Override //Æ¯Á¤ username(ID)À» ÀÔ·ÂÇÑ »ç¿ëÀÚÀÇ ¸ğµç Á¤º¸ Á¶È¸
    public UserVO getUserByUsername(String username) throws DataAccessException {
		return sqlSession.selectOne("mapper.user.getUserByUsername", username);
    }
	
	@Override // íšŒì› ì •ë³´ ìˆ˜ì • ë¡œì§ (ë¹„ë°€ë²ˆí˜¸ ë³€ê²½)
	public void updateUserWithPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithPassword",userVO);
	}
	
	@Override // íšŒì› ì •ë³´ ìˆ˜ì • ë¡œì§ (ë¹„ë°€ë²ˆí˜¸ ë¯¸ë³€ê²½)
	public void updateUserWithoutPassword(UserVO userVO) throws DataAccessException {
		sqlSession.update("mapper.user.updateUserWithoutPassword",userVO);
	}
}
