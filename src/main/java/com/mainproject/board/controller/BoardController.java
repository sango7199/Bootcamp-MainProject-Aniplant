package com.mainproject.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.board.vo.BoardVO;

public interface BoardController {


	public String listArticles(int categoryNum, Model model);
	
	public ModelAndView viewArticle(@RequestParam("post_num") int post_num, HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	

	 
	 
}
