package com.mainproject.board.dao;

import java.util.List;

import com.mainproject.board.vo.BoardVO;
import com.mainproject.paging.PagingVO;

public interface BoardDAO {

	// 페이징 정보를 기반으로 게시글 목록 조회
    public List<BoardVO> selectArticlesWithPaging(PagingVO paging) throws Exception;
    public List<BoardVO> selectArticlesByCategoryNumWithPaging(PagingVO paging) throws Exception;
    
    // 검색 조건에 따라 게시글 목록 검색
    public List<BoardVO> searchArticles(String searchType, String keyword, int startRow, int perPageNum) throws Exception;
    
    // 전체 게시글 수 조회
    public int getTotalCount() throws Exception;
    
    // 카테고리 번호에 따른 전체 게시글 수 조회
    public int getTotalCountByCategoryNum() throws Exception;
    
    // 검색 게시글 수 조회
    public int getSelectTotalCount(String searchType, String keyword) throws Exception;
    
	List<BoardVO> getArticlesByCategory(int categoryNum);

	BoardVO viewArticle(int post_num);

	void addNewArticle(BoardVO boardVO);

	void updateBoard(BoardVO board);

	void deleteBoard(int post_num);

	void increaseViews(int post_num);

	


	

	


	
	
}
