package com.mainproject.category.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.category.vo.CategoryVO;

public interface CategoryController {

    // 카테고리 목록을 조회하는 메서드
    // 페이지 번호와 페이지당 아이템 수를 받아와서 처리합니다.
    // ModelAndView를 반환하여 모델과 뷰를 설정합니다.
    public ModelAndView listCategories(HttpServletRequest request, HttpServletResponse response, int page, int perPageNum, boolean isSearch, Integer newPerPageNum) throws Exception;

    // 카테고리를 검색하는 메서드
    // 검색 유형, 검색어, 페이지 번호와 페이지당 아이템 수를 받아와서 처리합니다.
    // ModelAndView를 반환하여 모델과 뷰를 설정합니다.
    public ModelAndView searchCategories(String searchType, String keyword, int page, int perPageNum, boolean isSearch, Integer newPerPageNum) throws Exception;

    // 카테고리를 등록하는 메서드
    // ModelAndView를 반환하여 모델과 뷰를 설정합니다.
    public ModelAndView addCategory(CategoryVO categoryVO,HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView addCategory(CategoryVO categoryVO, HttpServletRequest request, HttpServletResponse response,
			String viewName) throws Exception;
}
