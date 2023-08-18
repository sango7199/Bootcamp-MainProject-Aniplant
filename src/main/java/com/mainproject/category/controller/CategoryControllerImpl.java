package com.mainproject.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.category.service.CategoryService;
import com.mainproject.category.vo.CategoryVO;
import com.mainproject.paging.Criteria;
import com.mainproject.paging.PagingVO;

@Controller("categoryController") // 스프링 컨테이너에 빈으로 등록되는 컨트롤러 클래스
public class CategoryControllerImpl implements CategoryController {
    @Autowired
    private CategoryService categoryService; // 카테고리 서비스 주입

    @Override
    @RequestMapping(value = "category/categories-list.do", method = RequestMethod.GET)
    public ModelAndView listCategories(HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "1") int page) throws Exception {
    	// 페이지당 아이템 수를 나타내는 변수
    	int perPageNum = 10; // 한 페이지에 보여줄 카테고리 아이템의 개수입니다.

    	// 뷰 이름을 가져오는 부분
    	String viewName = (String) request.getAttribute("viewName");
    	System.out.println(viewName);

    	// 페이지 번호를 기반으로 해당 페이지의 카테고리 목록을 조회합니다.
    	List<CategoryVO> categoriesList = categoryService.getCategoriesWithPaging(page, perPageNum);

    	// 페이징 관련 정보 생성 및 추가
    	PagingVO paging = new PagingVO();
    	paging.setCri(new Criteria(page, perPageNum)); // 현재 페이지와 페이지당 아이템 수로 Criteria 객체를 생성합니다.
    	paging.setTotalCount(categoryService.getTotalCount()); // 전체 아이템 수를 설정합니다.

    	// 이전 페이지 및 다음 페이지 계산
    	if (paging.getCri().getPage() > 1) {
    	    paging.setPrev(paging.getCri().getPage() - 1); // 현재 페이지가 2 이상일 경우 이전 페이지를 설정합니다.
    	} else {
    	    paging.setPrev(0); // 현재 페이지가 1이하일 경우 이전 페이지는 없습니다.
    	}

    	if (paging.getCri().getPage() < (int) Math.ceil((double) paging.getTotalCount() / perPageNum)) {
    	    paging.setNext(paging.getCri().getPage() + 1); // 현재 페이지가 마지막 페이지보다 작을 경우 다음 페이지를 설정합니다.
    	} else {
    	    paging.setNext(0); // 현재 페이지가 마지막 페이지일 경우 다음 페이지는 없습니다.
    	}

    	// 생성된 페이징 정보와 카테고리 목록을 ModelAndView 객체에 추가하여 반환합니다.
    	ModelAndView mav = new ModelAndView(viewName);
    	mav.addObject("categoriesList", categoriesList);
    	mav.addObject("paging", paging); // 페이징 정보를 뷰에 전달합니다.

    	return mav;
    }
}
