package com.mainproject.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mainproject.board.vo.BoardVO;

public interface BoardDAO {

	
	public BoardVO selectArticle(int post_num) throws DataAccessException;

	public void insertArticle(BoardVO boardVO);

	

	List<BoardVO> getArticlesByCategory(String categoryName);

	int selectNewPostNum();
	
}
