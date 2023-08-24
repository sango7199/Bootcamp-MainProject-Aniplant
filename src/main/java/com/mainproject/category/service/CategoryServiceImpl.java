package com.mainproject.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.category.dao.CategoryDAO;
import com.mainproject.category.vo.CategoryVO;
import com.mainproject.paging.Criteria;
import com.mainproject.paging.PagingVO;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<CategoryVO> listCategories() throws Exception {
        // 모든 카테고리 목록을 조회하기 위해 페이징 정보를 가진 PagingVO 객체를 생성하고 DAO에 전달합니다.
        return categoryDAO.selectCategoriesWithPaging(new PagingVO());
    }

    @Override
    public List<CategoryVO> getCategoriesWithPaging(int page, int perPageNum) throws Exception {
        // 요청한 페이지와 한 페이지당 보여줄 개수를 기반으로 페이징 정보를 설정하여 DAO에 전달합니다.
        PagingVO paging = new PagingVO();
        paging.setCri(new Criteria(page, perPageNum));
        return categoryDAO.selectCategoriesWithPaging(paging);
    }

    @Override
    public int getTotalCount() throws Exception {
        // 전체 카테고리 수를 조회하여 반환합니다.
        return categoryDAO.getTotalCount();
    }

    @Override
    public List<CategoryVO> searchCategories(String searchType, String keyword, int page, int perPageNum) throws Exception {
        // 검색 결과를 페이징 처리하기 위해 시작 행을 계산합니다.
        int startRow = (page - 1) * perPageNum;

        // 검색 쿼리를 실행하여 결과를 가져옴
        List<CategoryVO> searchResult = categoryDAO.searchCategories(searchType, keyword, startRow, perPageNum);
        return searchResult;
    }

	@Override
	public int getSelectTotalCount(String searchType, String keyword) throws Exception {
		// 전체 카테고리 수를 조회하여 반환합니다.
        return categoryDAO.getSelectTotalCount(searchType, keyword);
	}
}
