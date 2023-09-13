package com.mainproject.board.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mainproject.board.vo.BoardVO;

public interface BoardService {
	

 
	
	public BoardVO viewArticle(int post_num) throws Exception;


	public void addNewArticle(BoardVO boardVO);




	List<BoardVO> getArticlesByCategory(int categoryNum);


	void updateBoard(BoardVO board);


	void deleteBoard(int post_num);


	void increaseViews(int post_num);
	
//	추천
	void increaseGoodCount(int post_num);
//    비추천
	void increaseBadCount(int post_num);


	boolean hasVoted(int postNum, int createdUserNum, boolean voteType);


	void vote(int postNum, int createdUserNum, boolean voteType);


	void recordVote(int postNum, int createdUserNum, boolean voteType);





	
	





	



	



	
}
