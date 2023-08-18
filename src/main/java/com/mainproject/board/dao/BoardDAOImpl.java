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
	

	//리스트
	 @Override
	    public List<BoardVO> getArticlesByCategory(String categoryName) {
	        return sqlSession.selectList("mapper.board.selectArticlesByCategory", categoryName);
	    }
	
	 //등록
	@Override
    public void insertArticle(BoardVO boardVO) {
        sqlSession.insert("mapper.board.insertArticle", boardVO);
    }

	
	@Override
    public int selectNewPostNum() {
        return sqlSession.selectOne("mapper.board.selectNewpost_num");
    }



	
	@Override
	public BoardVO selectArticle(int post_num) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", post_num);
	}
	

}
