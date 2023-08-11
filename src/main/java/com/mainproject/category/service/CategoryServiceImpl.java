package com.mainproject.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.category.dao.CategoryDAO;

@Repository("categoryDAO")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List listCategories() throws DataAccessException {
		List categoriesList = null;
		categoriesList = categoryDAO.selectAllCategoriesList();
		return categoriesList;
	}

}
