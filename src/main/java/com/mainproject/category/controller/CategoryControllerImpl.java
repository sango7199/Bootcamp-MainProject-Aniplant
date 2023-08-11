package com.mainproject.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.category.service.CategoryService;

@Controller("categoryController")
public class CategoryControllerImpl implements CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Override
	@RequestMapping(value = "category/categories-list.do", method = RequestMethod.GET)
	public ModelAndView listCategories(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println(viewName);
		List categoriesList = categoryService.listCategories();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("categoriesList",categoriesList);
		return mav;
	}

}
