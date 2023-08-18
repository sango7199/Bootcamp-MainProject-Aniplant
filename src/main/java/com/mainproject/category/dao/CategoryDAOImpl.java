package com.mainproject.category.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mainproject.category.vo.CategoryVO;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CategoryVO> selectCategoriesWithPaging(int page, int perPageNum) throws Exception {
        // 현재 페이지에서 시작 로우를 계산합니다.
        // 예를 들어, 첫 번째 페이지에서는 (1 - 1) * 10 = 0이 되며, 두 번째 페이지에서는 (2 - 1) * 10 = 10이 됩니다.
        int startRow = (page - 1) * perPageNum;

        // MyBatis 파라미터로 사용할 맵을 생성합니다.
        Map<String, Integer> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("perPageNum", perPageNum);

        // 위에서 계산한 시작 로우부터 페이지당 아이템 수만큼의 카테고리 목록을 조회하는 메소드입니다.
        return sqlSession.selectList("mapper.category.selectCategoriesWithPaging", params);
    }

    @Override
    public int getTotalCount() throws Exception {
        // 총 아이템 수를 조회하는 쿼리를 실행하여 반환합니다.
        return sqlSession.selectOne("mapper.category.getTotalCount");
    }
}