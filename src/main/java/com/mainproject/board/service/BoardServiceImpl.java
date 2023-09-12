package com.mainproject.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mainproject.board.dao.BoardDAO;
import com.mainproject.board.vo.BoardVO;


@Repository("boardDAO")
public class BoardServiceImpl  implements BoardService{
	@Autowired
	private BoardDAO boardDAO;

	
	 @Override
	    public List<BoardVO> getArticlesByCategory(int categoryNum) {
	        return boardDAO.getArticlesByCategory(categoryNum);
	    }

	 @Override
	    public BoardVO viewArticle(int post_num) {
	        return boardDAO.viewArticle(post_num);
	    }

	    @Override
	    public void addNewArticle(BoardVO boardVO) {
	        boardDAO.addNewArticle(boardVO);
	    }
	    
	    //수정하기
	    
	    @Override
	    public void updateBoard(BoardVO board) {
	        boardDAO.updateBoard(board);
	    }
	    

		@Override
	    public void deleteBoard(int post_num) {
	        boardDAO.deleteBoard(post_num);
	    }
		
		//조회수 증가
		 @Override
		 public void increaseViews(int post_num) {
		     boardDAO.increaseViews(post_num);
		 }
		 
		 //추천
		 @Override
		    public void increaseGoodCount(int post_num) {
		        boardDAO.increaseGoodCount(post_num);
		  }
		 
		 //비추천
		  @Override
		   public void increaseBadCount(int post_num) {
		      boardDAO.increaseBadCount(post_num);
		  }
		  
		  

	

	

}
