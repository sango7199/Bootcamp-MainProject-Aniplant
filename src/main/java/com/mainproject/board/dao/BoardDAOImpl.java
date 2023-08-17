package com.mainproject.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<BoardVO> articlesList = null;
		articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}
	

	
	@Override
    public void insertArticle(BoardVO boardVO) {
        sqlSession.insert("mapper.board.insertArticle", boardVO);
    }





	
	@Override
	public BoardVO selectArticle(int post_num) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", post_num);
	}
	

}
