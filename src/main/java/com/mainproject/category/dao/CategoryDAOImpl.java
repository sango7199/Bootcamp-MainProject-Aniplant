package com.mainproject.category.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mainproject.category.vo.CategoryVO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllCategoriesList() throws DataAccessException {
		List<CategoryVO> categoriesList = null;
		categoriesList = sqlSession.selectList("mapper.category.selectAllCategoriesList");
		return categoriesList;
	}

}
