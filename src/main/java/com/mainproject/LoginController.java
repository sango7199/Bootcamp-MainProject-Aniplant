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
	            // ���� ����
	            attributes.addFlashAttribute("message", "�α��ο� �����߽��ϴ�.");
	            return "redirect:/dashboard"; // �α��� ���� �� �̵��� ������
	        } else {
	            // ���� ����
	            attributes.addFlashAttribute("error", "�߸��� ����ڸ� �Ǵ� ��й�ȣ�Դϴ�.");
	            return "redirect:/login"; // �α��� ���� �� �ٽ� �α��� �������� �̵�
	        }
	    }
}
