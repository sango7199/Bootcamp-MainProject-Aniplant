package com.mainproject.category.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mainproject.category.vo.CategoryVO;



import com.mainproject.paging.PagingVO;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	 @Override
	    public CategoryVO getCategoryByCategoryNum(int categoryNum) {
	        return sqlSession.selectOne("mapper.category.getCategoryByCategoryNum", categoryNum);
	    }


    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CategoryVO> selectCategoriesWithPaging(PagingVO paging) throws Exception {
        // mapper.xml에서 지정한 SQL 쿼리를 실행하여 페이징 정보를 기반으로 카테고리 목록을 조회합니다.
        return sqlSession.selectList("mapper.category.selectCategoriesWithPaging", paging);
    }

    @Override
    public List<CategoryVO> searchCategories(String searchType, String keyword, int startRow, int perPageNum) throws Exception {
        // 검색 조건과 키워드를 매개변수로 받아와서 파라미터 맵에 담습니다.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("searchType", searchType);
        parameters.put("keyword", keyword);
        parameters.put("startRow", startRow);
        parameters.put("perPageNum", perPageNum);

        // mapper.xml에서 지정한 SQL 쿼리를 실행하여 검색 조건에 맞는 카테고리 목록을 검색합니다.
        return sqlSession.selectList("mapper.category.searchCategories", parameters);
    }

    @Override
    public int getTotalCount() throws Exception {
        // mapper.xml에서 지정한 SQL 쿼리를 실행하여 전체 카테고리 수를 조회합니다.
        return sqlSession.selectOne("mapper.category.getTotalCount");
    }
    
	@Override
    public int getSelectTotalCount(String searchType, String keyword) throws Exception {
        // 검색 조건과 키워드를 매개변수로 받아와서 파라미터 맵에 담습니다.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("searchType", searchType);
        parameters.put("keyword", keyword);

        // mapper.xml에서 지정한 SQL 쿼리를 실행하여 검색 조건에 맞는 카테고리 수를 조회합니다.
        return sqlSession.selectOne("mapper.category.getSelectTotalCount", parameters);
    }

}
