package com.mainproject.board.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.board.service.BoardService;
import com.mainproject.board.vo.BoardVO;
import com.mainproject.category.service.CategoryService;
import com.mainproject.category.vo.CategoryVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	@Autowired
	private BoardService boardService;
	
	@Autowired
    private CategoryService categoryService; // CategoryService 주입
	
	@Autowired
	private BoardVO boardVO;
	
	
	 
	 // 카테고리 번호를 이용하여 게시글 목록 가져오기
   
	@Override
    @GetMapping("/board/listArticles.do")
    public String listArticles(@RequestParam("categoryNum") int categoryNum, Model model) {
        List<BoardVO> articlesList = boardService.getArticlesByCategory(categoryNum);
        CategoryVO category = getCategoryByCategoryNum(categoryNum);

        model.addAttribute("category", category);
        model.addAttribute("articlesList", articlesList);

        return "/board/listArticles"; 
    }

    private CategoryVO getCategoryByCategoryNum(int categoryNum) {
        return categoryService.getCategoryByCategoryNum(categoryNum);
    }

    // 게시글 등록 페이지
    @GetMapping("/board/articleForm.do")
    public String showArticleForm(Model model, Principal principal,HttpServletRequest request) {
//    	if (principal == null) {
//    		// 로그인하지 않은 경우 로그인 페이지로 이동
//           
//            return "redirect:/user/login.do";
//        }
    	
    	model.addAttribute("board", new BoardVO());
        return "board/articleForm"; 
    }

    @PostMapping("/board/articleForm.do")
    public String endArticle(@ModelAttribute("boardVO") BoardVO boardVO,@ModelAttribute("categoryVO") CategoryVO categoryVO, @RequestParam("categoryNum") int categoryNum, Principal principal, HttpServletRequest request) {
//    	if (principal == null) {
//    		// 로그인하지 않은 경우  로그인 페이지로 이동
//            
//            return "redirect:/user/login.do";
//        }
    	// 선택된 마지막 레벨의 카테고리를 Parent_category_num 필드로 설정
        if (categoryVO.getThirdLevelChildCategoryNum() != 0) {
            categoryVO.setCategory_num(categoryVO.getThirdLevelChildCategoryNum());
        } else if (categoryVO.getSecondLevelChildCategoryNum() != 0) {
            categoryVO.setCategory_num(categoryVO.getSecondLevelChildCategoryNum());
        } else if (categoryVO.getChildCategoryNum() != 0) {
            categoryVO.setCategory_num(categoryVO.getChildCategoryNum());
        } else if (categoryVO.getParentCategoryNum() != 0) {
            categoryVO.setCategory_num(categoryVO.getParentCategoryNum());
        }
    	
    	boardVO.setCategory_num(categoryVO.getCategory_num()); // 카테고리 번호 설정
        boardService.addNewArticle(boardVO);
        return "redirect:/board/listArticles.do?categoryNum=" + boardVO.getCategory_num();
    }
    

    
 // 게시글 보기 페이지
    @GetMapping("/board/viewArticle.do")
    public ModelAndView viewArticle(@RequestParam("post_num") int post_num,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        boardVO = boardService.viewArticle(post_num);
        
        // 조회수 증가 처리
        boardService.increaseViews(post_num);
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject("board", boardVO);
        

        return mav;
    }
    
    //게시글 수정
    @PostMapping("/edit/{post_num}")
    public String editBoard(@PathVariable int post_num, @ModelAttribute BoardVO updatedBoard) {
    	boardService.updateBoard(updatedBoard);
        return "redirect:/board/viewArticle.do?post_num=" + post_num; // 수정된 게시글로 리다이렉트
    }
    
    //게시글 삭제
    @PostMapping("/board/delete/{post_num}")
    public String deleteBoard(@PathVariable int post_num) {
        boardService.deleteBoard(post_num);
        return "redirect:/board/listArticles.do?categoryNum=" + boardVO.getCategory_num();
    }


}
	

