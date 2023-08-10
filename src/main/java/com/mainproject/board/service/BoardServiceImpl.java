package com.mainproject.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mainproject.board.dao.BoardDAO;

@Service("boardService")
public class BoardServiceImpl  implements BoardService{
	@Autowired
	BoardDAO boardDAO;
}
