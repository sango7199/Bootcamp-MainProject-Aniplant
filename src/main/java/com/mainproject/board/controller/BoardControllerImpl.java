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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.board.service.BoardService;
import com.mainproject.board.vo.BoardVO;
import com.mainproject.category.vo.CategoryVO;
import com.mainproject.user.vo.UserVO;

import org.springframework.ui.Model;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardVO boardVO;
	
//	//게시글 리스트 페이지
	@Override
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		List articlesList = boardService.listArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList", articlesList);
		return mav;	
	}
	
//	@GetMapping("/board/animal.do")
//	public String getAnimalBoard(Model model) {
//		List<BoardVO> animalArticles = boardService.getArticlesByCategory("동물");// 동물 게시판 글 목록 조회
//		model.addAttribute("articlesList",animalArticles);
//		 model.addAttribute("category", new CategoryVO("동물")); // 카테고리 정보 추가
//		 return "/board/listArticles";
//	}
	
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

	
	
	
	
	
	
	
	
	
	



