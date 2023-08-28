package com.mainproject.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.category.service.CategoryService;
import com.mainproject.category.vo.CategoryVO;
import com.mainproject.paging.PagingUtils;
import com.mainproject.paging.PagingVO;
import com.mainproject.searching.SearchingUtils;
import com.mainproject.user.vo.UserVO;

@Controller("categoryController")
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerImpl(CategoryService categoryService, SearchingUtils searchingUtils) {
        this.categoryService = categoryService;
    }
    
    @Autowired
    private HttpServletRequest request;

    // 카테고리 리스트
    @Override
    @RequestMapping(value = "/category/categories-list.do", method = RequestMethod.GET)
    public ModelAndView listCategories(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPageNum,
            @RequestParam(defaultValue = "false") boolean isSearch,
            @RequestParam(required = false) Integer newPerPageNum) throws Exception {

        String viewName = (String) request.getAttribute("viewName");
        List<CategoryVO> categoriesList;
        int totalCount;
        PagingVO paging;

        if (newPerPageNum != null) {
            // 페이지당 아이템 수가 변경된 경우의 로직
        	// System.out.println(newPerPageNum+"=====================");
            categoriesList = categoryService.getCategoriesWithPaging(page, newPerPageNum);
            totalCount = categoryService.getTotalCount();
            paging = PagingUtils.createPaging(page, newPerPageNum, totalCount, isSearch);
        } else {
            // 기존의 카테고리 목록 조회 로직
            categoriesList = categoryService.getCategoriesWithPaging(page, perPageNum);
            totalCount = categoryService.getTotalCount();
            paging = PagingUtils.createPaging(page, perPageNum, totalCount, isSearch);
        }
        
        // 페이지 계산 및 페이징 정보 설정
        List<Integer> pageNumbers = PagingUtils.calculatePageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());
        List<Integer> blockPageNumbers = PagingUtils.calculateBlockPageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());
        
        // ModelAndView 객체 생성 및 데이터 설정
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("categoriesList", categoriesList);
        mav.addObject("paging", paging);
        mav.addObject("pageNumbers", pageNumbers);
        mav.addObject("blockPageNumbers", blockPageNumbers);
        return mav;
    }

    
    // 카테고리 리스트 검색
    @Override
    @RequestMapping(value = "/category/categories-list-search.do", method = RequestMethod.GET)
    public ModelAndView searchCategories(
            @RequestParam("searchType") String searchType,
            @RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPageNum,
            @RequestParam(defaultValue = "false") boolean isSearch,
            @RequestParam(required = false) Integer newPerPageNum) throws Exception {
       
        String viewName = (String) request.getAttribute("viewName");

        // 새로운 페이지당 아이템 수가 지정된 경우, 해당 값을 사용하도록 설정
        if (newPerPageNum != null) {
            perPageNum = newPerPageNum;
        }

        // 검색 결과에 따른 아이템 총 개수를 조회
        int totalCount = categoryService.getSelectTotalCount(searchType, keyword);

        // 검색 결과에 따른 페이징 정보 생성
        PagingVO paging = PagingUtils.createPaging(page, perPageNum, totalCount, true);

        // 페이지 계산 및 페이징 정보 설정
        List<Integer> pageNumbers = PagingUtils.calculatePageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());
        List<Integer> blockPageNumbers = PagingUtils.calculateBlockPageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());

        // 검색 결과에 따른 카테고리 목록 조회
        List<CategoryVO> categoriesList = categoryService.searchCategories(searchType, keyword, page, perPageNum);

        // ModelAndView 객체 생성 및 데이터 설정
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("searchType", searchType);
        mav.addObject("keyword", keyword);
        mav.addObject("categoriesList", categoriesList);
        mav.addObject("paging", paging);
        mav.addObject("pageNumbers", pageNumbers);
        mav.addObject("blockPageNumbers", blockPageNumbers);

        return mav;
    }

    // 카테고리 등록
    @Override
    @RequestMapping(value = "/category/add-category.do", method = RequestMethod.GET)
	public ModelAndView addCategory(CategoryVO categoryVO, HttpServletRequest request, HttpServletResponse response, String viewName) throws Exception {
    	ModelAndView mav = new ModelAndView(viewName);
    	
		return mav;
	}


	@Override
	public ModelAndView addCategory(CategoryVO categoryVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	


}
