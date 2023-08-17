package com.mainproject.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.category.dao.CategoryDAO;
import com.mainproject.category.vo.CategoryVO;

@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public CategoryVO getCategoryByName(String categoryName) {
        return categoryDAO.getCategoryByName(categoryName);
    }

}
