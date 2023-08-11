package com.mainproject.category.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface CategoryController {
	public ModelAndView listCategories(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
