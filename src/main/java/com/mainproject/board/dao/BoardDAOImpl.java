package com.mainproject.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.board.vo.BoardVO;
import com.mainproject.board.vo.VoteVO;

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
	 
	 @Override
	    public void deleteBoard(int post_num) {
	        sqlSession.update("mapper.board.deleteBoard", post_num);
	    }
	 
	 @Override
	    public void increaseViews(int post_num) {
	        sqlSession.update("mapper.board.increaseViews", post_num);
	    }
	 
	 //추천
	 @Override
	    public void increaseGoodCount(int post_num) {
	        sqlSession.update("mapper.board.updateGoodCount", post_num);
	    }
	 //비추천
	    @Override
	    public void increaseBadCount(int post_num) {
	        sqlSession.update("mapper.board.updateBadCount", post_num);
	    }


	    @Override
	    public boolean hasVoted(int postNum, int createdUserNum, boolean voteType) {
	        int count = sqlSession.selectOne("mapper.vote.hasVoted", postNum);
	        return count > 0;
	    }

	    @Override
	    public void recordVote(VoteVO voteVO) {
	        sqlSession.insert("mapper.vote.recordVote", voteVO);
	    }
	 
	    
	    
	    
	
	 
	 

	}
	


