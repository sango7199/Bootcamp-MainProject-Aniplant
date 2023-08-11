package com.mainproject.category.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface CategoryDAO {
	public List selectAllCategoriesList() throws DataAccessException;
}
