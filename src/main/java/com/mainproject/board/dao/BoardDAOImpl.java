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
    public List<BoardVO> getArticlesByCategory(int categoryNum) {
        return sqlSession.selectList("mapper.board.selectArticlesByCategory", categoryNum);
    }

    
	@Override
    public BoardVO viewArticle(int post_num) {
        return sqlSession.selectOne("mapper.board.selectArticleByPostNum", post_num);
    }

   
	@Override
    public void addNewArticle(BoardVO boardVO) {
        sqlSession.insert("mapper.board.insertArticle", boardVO);
    }
	
	//수정하기
	 @Override
	    public void updateBoard(BoardVO board) {
	        sqlSession.update("mapper.board.updateBoard", board);
	    }
	 
	 //삭제하기
	 @Override
	    public void deleteBoard(int postNum) {
	        sqlSession.delete("mapper.board.deleteBoard", postNum);
	    }

	}
	


