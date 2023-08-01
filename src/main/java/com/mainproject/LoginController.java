package com.mainproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
		@Autowired
		private LoginService loginService;
	
	  @GetMapping("/login")
	    public ModelAndView main() {
	    	ModelAndView mav = new ModelAndView();
	        mav.setViewName("user/login");
	        return mav;
	    }
	  
	  @PostMapping("/login")
	    public String login(@RequestParam String id, @RequestParam String password, RedirectAttributes attributes) {
	        if (loginService.authenticate(id, password)) {
	            // 인증 성공
	            attributes.addFlashAttribute("message", "로그인에 성공했습니다.");
	            return "redirect:/dashboard"; // 로그인 성공 시 이동할 페이지
	        } else {
	            // 인증 실패
	            attributes.addFlashAttribute("error", "잘못된 사용자명 또는 비밀번호입니다.");
	            return "redirect:/login"; // 로그인 실패 시 다시 로그인 페이지로 이동
	        }
	    }
}
