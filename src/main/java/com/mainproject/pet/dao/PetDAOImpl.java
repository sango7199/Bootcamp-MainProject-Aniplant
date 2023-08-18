package com.mainproject.pet.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.pet.vo.PetVO;

@Repository("petDAO")
public class PetDAOImpl implements PetDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override 
    public List<PetVO> getAllPet() throws DataAccessException {
        return sqlSession.selectList("mapper.pet.getAllPet");
    }

	@Override
	public int registerPet(PetVO petVO) throws DataAccessException {
		return sqlSession.insert("mapper.pet.registerPet", petVO);
	}

	
}
