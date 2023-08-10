package com.mainproject.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.board.service.BoardService;
import com.mainproject.board.vo.BoardVO;

@Controller("boardController")
public class BoardControllerImpl  implements BoardController{
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardVO boardVO;
	


	}


