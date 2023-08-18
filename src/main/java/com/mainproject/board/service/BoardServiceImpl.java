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
	    
	    
//		@Override
//	    public void editArticle(BoardVO boardVO) {
//	        boardDAO.editArticle(boardVO);
//	    }
//
//	    
//		@Override
//	    public void deleteArticle(int post_num) {
//	        boardDAO.deleteArticle(post_num);
//	    }

	

	

}
