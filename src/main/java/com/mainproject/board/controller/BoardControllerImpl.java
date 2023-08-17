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
	

	 
	 @Override
	 @GetMapping("/board/listArticles.do")
	    public String listArticles(@RequestParam("categoryName") String categoryName, Model model) {
	        List<BoardVO> articlesList = boardService.getArticlesByCategory(categoryName);
	        CategoryVO category = getCategoryByName(categoryName);
	        
	        model.addAttribute("category", category);
	        model.addAttribute("articlesList", articlesList);
	        
	        return "/board/listArticles";
	    }
	 
	 private CategoryVO getCategoryByName(String categoryName) {
	        // 여기서 실제로 categoryName으로 CategoryVO를 가져오는 코드를 구현해야합니다.
	        // CategoryService를 사용하여 DB로부터 데이터를 가져오도록 작성해야합니다.
	        return categoryService.getCategoryByName(categoryName);
	    }

	 
	//게시글 등록 페이지
	 @GetMapping("/board/articleForm.do")
	    public String showarticleForm(Model model) {
	        model.addAttribute("board", new BoardVO()); // 빈 BoardVO 객체를 모델에 추가하여 폼을 띄웁니다.
	        return "board/articleForm"; // 적절한 뷰 이름을 반환해야 합니다.
	    }

	    @PostMapping("/board/articleForm.do")
	    public String endarticle(@ModelAttribute("boardVO") BoardVO boardVO) {
	        boardService.addNewArticle(boardVO);
	        return "redirect:/board/listArticles.do"; // 등록 후 게시글 목록 페이지로 리다이렉트합니다.
	    }
	
	

	@RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("post_num") int post_num,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		boardVO=boardService.viewArticle(post_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("board", boardVO);
		return mav;
	}


	
	
}

	
	
	
	
	
	
	
	
	
	



