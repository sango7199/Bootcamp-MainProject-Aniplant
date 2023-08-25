package com.mainproject.category.dao;


import com.mainproject.category.vo.CategoryVO;

import java.util.List;

import com.mainproject.category.vo.CategoryVO;
import com.mainproject.paging.PagingVO;

public interface CategoryDAO {
	CategoryVO getCategoryByCategoryNum(int categoryNum);

    // 페이징 정보를 기반으로 카테고리 목록 조회
    List<CategoryVO> selectCategoriesWithPaging(PagingVO paging) throws Exception;

    // 검색 조건에 따라 카테고리 목록 검색
    List<CategoryVO> searchCategories(String searchType, String keyword, int startRow, int perPageNum) throws Exception;

    // 전체 카테고리 수 조회
    int getTotalCount() throws Exception;
    
    // 검색 카테고리 수 조회
	int getSelectTotalCount(String searchType, String keyword) throws Exception;

}
