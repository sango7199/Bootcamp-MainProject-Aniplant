package com.mainproject.category.service;

import java.util.List;

import com.mainproject.category.vo.CategoryVO;

public interface CategoryService {
    // 모든 카테고리 목록을 조회하는 메소드
    List<CategoryVO> listCategories() throws Exception;
    
    // 페이징 처리된 카테고리 목록을 조회하는 메소드
    List<CategoryVO> getCategoriesWithPaging(int page, int perPageNum) throws Exception;
    
    // 전체 아이템 수를 가져오는 메소드
    int getTotalCount() throws Exception;
}
