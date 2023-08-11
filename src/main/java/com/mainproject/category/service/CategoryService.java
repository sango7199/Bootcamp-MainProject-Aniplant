package com.mainproject.category.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface CategoryService {
	public List listCategories() throws DataAccessException;
}
