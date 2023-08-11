package com.mainproject.board.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.board.vo.BoardVO;

public interface BoardService {
	 public List listArticles() throws DataAccessException;

}
