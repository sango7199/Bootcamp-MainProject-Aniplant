package com.mainproject.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mainproject.board.vo.BoardVO;

public interface BoardDAO {



	List<BoardVO> getArticlesByCategory(int categoryNum);

	BoardVO viewArticle(int post_num);

	void addNewArticle(BoardVO boardVO);

	void updateBoard(BoardVO board);

	void deleteBoard(int post_num);

	void increaseViews(int post_num);


	

	


	
	
}
