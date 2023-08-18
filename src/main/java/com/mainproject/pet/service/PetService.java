package com.mainproject.pet.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.pet.vo.PetVO;

public interface PetService {
	public void registerPet(PetVO petVO) throws DataAccessException;
	public List<PetVO> getAllPet() throws DataAccessException;
}
