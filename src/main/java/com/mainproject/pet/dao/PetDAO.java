package com.mainproject.pet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.pet.vo.PetVO;

public interface PetDAO {
	public List<PetVO> getAllPet(int userNum) throws DataAccessException;

	public int registerPet(PetVO petVO) throws DataAccessException;

	public PetVO findByPetNo(int petNo) throws DataAccessException;
	
	public void updatePet(PetVO petVO) throws DataAccessException;
	
	public void deletePet(PetVO petVO) throws DataAccessException;
}
