package com.mainproject.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.category.dao.CategoryDAO;
import com.mainproject.category.vo.CategoryVO;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<CategoryVO> listCategories() throws Exception {
        // 예시로 1페이지, 10개씩 조회한 결과를 반환
        return categoryDAO.selectCategoriesWithPaging(1, 10);
    }

    @Override
    public List<CategoryVO> getCategoriesWithPaging(int page, int perPageNum) throws Exception {
        // 지정된 페이지와 페이지당 아이템 수로 카테고리 목록을 조회한 결과를 반환
        return categoryDAO.selectCategoriesWithPaging(page, perPageNum);
    }

    @Override
    public int getTotalCount() throws Exception {
        // 총 아이템 수를 조회하여 반환
        return categoryDAO.getTotalCount();
    }
}
