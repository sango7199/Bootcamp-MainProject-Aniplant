package com.mainproject.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mainproject.board.vo.BoardVO;

public interface BoardService {
	
	public List listArticles() throws DataAccessException;
 
	
	public BoardVO viewArticle(int post_num) throws Exception;


	void addNewArticle(BoardVO boardVO);

	
}
