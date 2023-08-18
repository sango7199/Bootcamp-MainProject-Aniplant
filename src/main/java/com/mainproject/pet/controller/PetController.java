package com.mainproject.pet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.pet.vo.PetVO;

public interface PetController {
	public ModelAndView viewPetJoin(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<String> registerPet(@RequestBody PetVO petVO);
}
