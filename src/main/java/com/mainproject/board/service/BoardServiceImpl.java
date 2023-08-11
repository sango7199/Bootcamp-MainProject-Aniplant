package com.mainproject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mainproject.board.dao.BoardDAO;
import com.mainproject.board.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl  implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public List listArticles() throws DataAccessException {
		List articlesList = null;
		articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}
	

}
