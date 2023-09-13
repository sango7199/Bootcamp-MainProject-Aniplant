package com.mainproject.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.mainproject.board.vo.BoardVO;
import com.mainproject.board.vo.VoteVO;

public interface BoardDAO {



	List<BoardVO> getArticlesByCategory(int categoryNum);

	BoardVO viewArticle(int post_num);

	void addNewArticle(BoardVO boardVO);

	void updateBoard(BoardVO board);

	void deleteBoard(int post_num);

	void increaseViews(int post_num);
	
	//추천
	void increaseGoodCount(int post_num);
    //비추천
	void increaseBadCount(int post_num);
	
	boolean hasVoted(int postNum, int createdUserNum, boolean voteType);

    void recordVote(VoteVO voteVO);


	

	


	
	
}
