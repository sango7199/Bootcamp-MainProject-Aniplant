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
	public List listArticles() throws DataAccessException {
		List articlesList = null;
		articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}
	
	
	@Override
    public void addNewArticle(BoardVO boardVO) {
        if (boardVO != null && boardVO.getTitle() != null && !boardVO.getTitle().isEmpty()) {
            boardDAO.insertArticle(boardVO);
        } else {
            throw new IllegalArgumentException("Board title cannot be null or empty.");
        }
    }
	

	  
	 //단일 파일 보이기
		@Override
		public BoardVO viewArticle(int post_num) throws Exception {
			BoardVO boardVO = boardDAO.selectArticle(post_num);
			return boardVO;
		}

	

	

}
