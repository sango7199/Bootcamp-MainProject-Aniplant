package com.mainproject.category.dao;

import com.mainproject.category.vo.CategoryVO;

public interface CategoryDAO {
//	CategoryVO getCategoryByName(String categoryName);

	CategoryVO getCategoryByCategoryNum(int categoryNum);

}
