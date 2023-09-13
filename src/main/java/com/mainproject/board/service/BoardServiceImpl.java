package com.mainproject.board.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mainproject.board.dao.BoardDAO;
import com.mainproject.board.vo.BoardVO;
import com.mainproject.board.vo.VoteVO;
import com.mainproject.user.service.UserService;
import com.mainproject.user.vo.UserVO;


@Repository("boardDAO")
public class BoardServiceImpl  implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private UserService userService;

	
	 @Override
	    public List<BoardVO> getArticlesByCategory(int categoryNum) {
	        return boardDAO.getArticlesByCategory(categoryNum);
	    }

	 @Override
	    public BoardVO viewArticle(int post_num) {
	        return boardDAO.viewArticle(post_num);
	    }

	 @Override
	 public void addNewArticle(BoardVO boardVO) {
		 
	    boardDAO.addNewArticle(boardVO);
	 }
	    
	    //수정하기
	    
	    @Override
	    public void updateBoard(BoardVO board) {
	        boardDAO.updateBoard(board);
	    }
	    

		@Override
	    public void deleteBoard(int post_num) {
	        boardDAO.deleteBoard(post_num);
	    }
		
		//조회수 증가
		 @Override
		 public void increaseViews(int post_num) {
		     boardDAO.increaseViews(post_num);
		 }
		 
		 //추천
		 @Override
		    public void increaseGoodCount(int post_num) {
		        boardDAO.increaseGoodCount(post_num);
		  }
		 
		 //비추천
		  @Override
		   public void increaseBadCount(int post_num) {
		      boardDAO.increaseBadCount(post_num);
		  }
		  
		  
		  @Override
		    public boolean hasVoted(int postNum, int createdUserNum, boolean voteType) {
		        // 해당 게시글에 대한 투표 여부 확인하는 로직
		        // boardDAO를 사용하여 TB_VOTE 테이블을 조회하여 해당 게시글과 유저에 대한 투표 기록이 있는지 확인.
		        return boardDAO.hasVoted(postNum, createdUserNum, voteType);
		    }

		    @Override
		    @Transactional
		    public void vote(int postNum, int createdUserNum, boolean voteType) {
		        // TB_BOARD 테이블의 Good 또는 Bad 값 업데이트
		        if (voteType == true) {
		            // 추천인 경우
		            boardDAO.increaseGoodCount(postNum);
		        } else {
		            // 비추천인 경우
		            boardDAO.increaseBadCount(postNum);
		        }
		    }

		    @Override
		    @Transactional
		    public void recordVote(int postNum, int createdUserNum, boolean voteType) {
		        // TB_VOTE에 투표 기록 저장
		        VoteVO voteVO = new VoteVO();
		        voteVO.setPost_num(postNum);
		    }
		  
		  

	

	

}
