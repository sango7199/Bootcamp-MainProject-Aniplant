package com.mainproject.category.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

// 카테고리 컨트롤러 인터페이스
public interface CategoryController {

    // 카테고리 목록 조회 메서드
    // 페이지 번호를 받아와서 해당 페이지의 카테고리 목록을 조회하고,
    // 페이징 처리된 ModelAndView 객체를 반환하는 메서드
    public ModelAndView listCategories(HttpServletRequest request, HttpServletResponse response, int page) throws Exception;
}
