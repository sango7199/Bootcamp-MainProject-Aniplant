package com.mainproject.category.service;


import com.mainproject.category.vo.CategoryVO;






import java.util.List;

import com.mainproject.category.vo.CategoryVO;

public interface CategoryService {
	
	CategoryVO getCategoryByCategoryNum(int categoryNum);


	
    // 모든 카테고리 목록 조회
	public List<CategoryVO> listCategories() throws Exception;

    // 페이징 정보를 기반으로 특정 페이지의 카테고리 목록 조회
	public List<CategoryVO> getCategoriesWithPaging(int page, int perPageNum) throws Exception;

    // 전체 카테고리 수 조회
	public int getTotalCount() throws Exception;

    // 검색 조건과 키워드를 이용하여 카테고리 검색
	public List<CategoryVO> searchCategories(String searchType, String keyword, int page, int perPageNum) throws Exception;

    // 검색 카테고리 수 조회
	public int getSelectTotalCount(String searchType, String keyword) throws Exception;
}
