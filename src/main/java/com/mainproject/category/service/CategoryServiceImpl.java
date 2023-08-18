package com.mainproject.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.category.dao.CategoryDAO;
import com.mainproject.category.vo.CategoryVO;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
    private CategoryDAO categoryDAO;

	
	@Override
	    public CategoryVO getCategoryByCategoryNum(int categoryNum) {
	        return categoryDAO.getCategoryByCategoryNum(categoryNum);
	    }

}
