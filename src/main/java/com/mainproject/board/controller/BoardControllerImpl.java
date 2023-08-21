package com.mainproject.board.controller;

import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.board.service.BoardService;
import com.mainproject.board.vo.BoardVO;
import com.mainproject.category.service.CategoryService;
import com.mainproject.category.vo.CategoryVO;
import com.mainproject.user.vo.UserVO;

import org.springframework.ui.Model;

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
    public String showArticleForm(Model model) {
        model.addAttribute("board", new BoardVO());
        return "board/articleForm"; 
    }

    @PostMapping("/board/articleForm.do")
    public String endArticle(@ModelAttribute("boardVO") BoardVO boardVO, @RequestParam("categoryNum") int categoryNum) {
        boardVO.setCategory_num(categoryNum); // 카테고리 번호 설정
        boardService.addNewArticle(boardVO);
        return "redirect:/board/listArticles.do?categoryNum=" + categoryNum;
    }
    
 // 게시글 보기 페이지
    @GetMapping("/board/viewArticle.do")
    public ModelAndView viewArticle(@RequestParam("post_num") int post_num,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        boardVO = boardService.viewArticle(post_num);
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
    
    //삭제
    @PostMapping("/delete/{post_num}")
    public String deleteBoard(@PathVariable int post_num) {
        boardService.deleteBoard(post_num);
        return "redirect:/board/listArticles.do"; // 게시글 목록 페이지로 리다이렉트
    }


  

    

}
	
	
	
	
	
	
	
	



