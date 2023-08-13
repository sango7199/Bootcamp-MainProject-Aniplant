package com.mainproject.captcha;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CaptchaController {

	@Autowired
	CaptchaService captchaService;

	// captcha 요청 로직
	@GetMapping("/api/captcha-load")
	public ResponseEntity<?> loadCaptcha() {
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("captchaUrl", "/captcha.do");

		return ResponseEntity.ok(responseMap);
	}

	// captcha 페이지 로드 로직
	@RequestMapping(value = { "/captcha.do" }, method = RequestMethod.GET)
	public ModelAndView viewJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// captcha 이미지 생성 로직
	@RequestMapping(value = "/api/captcha-image", method = RequestMethod.GET)
	public ResponseEntity<?> getCaptchaImage() {
		try {
			String captchaKey = captchaService.generateCaptchaKey();
			String captchaImage = captchaService.getCaptchaImageAsBase64(captchaKey);

			Map<String, String> response = new HashMap<>();
			response.put("captchaKey", captchaKey);
			response.put("captchaImage", captchaImage);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("CAPTCHA 이미지를 가져오는 중 오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
