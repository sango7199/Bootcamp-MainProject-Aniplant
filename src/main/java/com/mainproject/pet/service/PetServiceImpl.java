package com.mainproject.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mainproject.pet.dao.PetDAO;
import com.mainproject.pet.vo.PetVO;


@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetDAO petDAO;

	@Override
	public void registerPet(PetVO petVO) throws DataAccessException {
		petDAO.registerPet(petVO);
	}

	@Override 
	public List<PetVO> getAllPet() throws DataAccessException {
	        return petDAO.getAllPet();
	}
	
}
