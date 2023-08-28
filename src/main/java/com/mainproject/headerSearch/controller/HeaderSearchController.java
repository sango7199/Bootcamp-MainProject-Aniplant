package com.mainproject.headerSearch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface HeaderSearchController {
	
	public ModelAndView viewSearchResult(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
