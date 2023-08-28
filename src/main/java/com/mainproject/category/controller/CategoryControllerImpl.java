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
            
        	// System.out.println(newPerPageNum+"=====================");
            categoriesList = categoryService.getCategoriesWithPaging(page, newPerPageNum);
            totalCount = categoryService.getTotalCount();
            paging = PagingUtils.createPaging(page, newPerPageNum, totalCount, isSearch);
        } else {
           
            categoriesList = categoryService.getCategoriesWithPaging(page, perPageNum);
            totalCount = categoryService.getTotalCount();
            paging = PagingUtils.createPaging(page, perPageNum, totalCount, isSearch);
        }
        
       
        List<Integer> pageNumbers = PagingUtils.calculatePageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());
        List<Integer> blockPageNumbers = PagingUtils.calculateBlockPageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());
        
    
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

        
        if (newPerPageNum != null) {
            perPageNum = newPerPageNum;
        }

       
        int totalCount = categoryService.getSelectTotalCount(searchType, keyword);

       
        PagingVO paging = PagingUtils.createPaging(page, perPageNum, totalCount, true);

     
        List<Integer> pageNumbers = PagingUtils.calculatePageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());
        List<Integer> blockPageNumbers = PagingUtils.calculateBlockPageNumbers(paging.getCurrentBlock(), paging.getPageCount(), paging.getTotalPage());

        
        List<CategoryVO> categoriesList = categoryService.searchCategories(searchType, keyword, page, perPageNum);

       
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
