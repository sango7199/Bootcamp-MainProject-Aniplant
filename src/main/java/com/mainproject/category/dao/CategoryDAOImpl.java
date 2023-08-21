package com.mainproject.category.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mainproject.category.vo.CategoryVO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
    private SqlSession sqlSession;

	 @Override
	    public CategoryVO getCategoryByCategoryNum(int categoryNum) {
	        return sqlSession.selectOne("mapper.category.getCategoryByCategoryNum", categoryNum);
	    }

}
