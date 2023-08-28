package com.mainproject.pet.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.mainproject.pet.vo.PetVO;

public interface PetController {
	public ModelAndView viewPetJoin(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<Map<String, String>> registerPet(@RequestBody PetVO petVO) throws Exception;
	public String getPetsList(Model model) throws Exception;
	public ModelAndView viewPetInfo(@PathVariable("id") int petNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewPetUpdate(@PathVariable("id") int petNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<Map<String, String>> updatedPet(@RequestBody PetVO petVO) throws Exception;
	public ResponseEntity<Map<String, String>> deletePet(@RequestBody PetVO petVO) throws Exception;
}
