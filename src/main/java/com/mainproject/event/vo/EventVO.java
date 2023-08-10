package com.mainproject.event.vo;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("eventVO")
public class EventVO {
	
	private int event_num;
	private String tile;
	private Timestamp stared_at;
	private Timestamp ended_at;
	private String location;
	private String type;
	private String is_admin;
	private String created_user_num;
	private Timestamp created_at;
	private int updated_user_num;
	private Timestamp updated_at; 
	private boolean is_deleted;
	private int deleted_user_num;
	private Timestamp deleted_at;
	
	public EventVO() {
	
	}

	public int getEvent_num() {
		return event_num;
	}

	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

	public Timestamp getStared_at() {
		return stared_at;
	}

	public void setStared_at(Timestamp stared_at) {
		this.stared_at = stared_at;
	}

	public Timestamp getEnded_at() {
		return ended_at;
	}

	public void setEnded_at(Timestamp ended_at) {
		this.ended_at = ended_at;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}

	public String getCreated_user_num() {
		return created_user_num;
	}

	public void setCreated_user_num(String created_user_num) {
		this.created_user_num = created_user_num;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public int getUpdated_user_num() {
		return updated_user_num;
	}

	public void setUpdated_user_num(int updated_user_num) {
		this.updated_user_num = updated_user_num;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public int getDeleted_user_num() {
		return deleted_user_num;
	}

	public void setDeleted_user_num(int deleted_user_num) {
		this.deleted_user_num = deleted_user_num;
	}

	public Timestamp getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Timestamp deleted_at) {
		this.deleted_at = deleted_at;
	}

}
