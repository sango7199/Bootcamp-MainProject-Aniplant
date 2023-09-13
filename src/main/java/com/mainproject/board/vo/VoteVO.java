package com.mainproject.board.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("voteVO")
public class VoteVO {
	private int vote_num;
	private int post_num;
	private int created_user_num;
	private boolean is_voted;
	private Timestamp created_at; 
	
	public VoteVO() {
		
	}
	
	
	public int getVote_num() {
		return vote_num;
	}
	public boolean isIs_voted() {
		return is_voted;
	}
	public void setIs_voted(boolean is_voted) {
		this.is_voted = is_voted;
	}
	public void setVote_num(int vote_num) {
		this.vote_num = vote_num;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public int getCreated_user_num() {
		return created_user_num;
	}
	public void setCreated_user_num(int created_user_num) {
		this.created_user_num = created_user_num;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	
}
