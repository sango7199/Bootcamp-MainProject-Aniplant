package com.mainproject.category.dao;

import java.util.List;

import com.mainproject.category.vo.CategoryVO;

public interface CategoryDAO {
    // 특정 페이지의 카테고리 목록을 가져오는 메소드
    List<CategoryVO> selectCategoriesWithPaging(int page, int perPageNum) throws Exception;
    
    // 전체 아이템 수를 가져오는 메소드
    int getTotalCount() throws Exception;
}
