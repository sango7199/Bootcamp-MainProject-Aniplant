package com.mainproject.pet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.pet.service.PetService;
import com.mainproject.pet.vo.PetVO;
import com.mainproject.user.vo.CustomUserDetails;
import com.mainproject.user.vo.UserVO;

@Controller("petController")
public class PetControllerImpl implements PetController {
	@Autowired
	private PetService petService;

	@Autowired
	PetVO petVO;

	@Override // 반려친구 등록페이지
	@RequestMapping(value = { "/mypage/pet-join.do" }, method = RequestMethod.GET)
	public ModelAndView viewPetJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@Override // 회원가입 로직
	@PostMapping("/api/register-pet")
	public ResponseEntity<String> registerPet(@RequestBody PetVO petVO) {
		try {
			// 로그인한 사용자의 CustomUserDetails 정보를 가져오기
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			// CustomUserDetails를 통해 원래 UserVO 객체를 얻기
			UserVO userVO = userDetails.getUser();

			// 회원 번호를 얻기
			int userNum = userVO.getUser_num();

			// 회원 번호를 petVO 객체에 설정
			petVO.setCreated_user_num(userNum);

			// Pet 등록 서비스 호출
			petService.registerPet(petVO);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/mypage/pet-list.do")
	public String getPetsList(Model model) {
		List<PetVO> petList = petService.getAllPet();
		model.addAttribute("petList", petList);
		return "mypage/pet-list";

	}
}
