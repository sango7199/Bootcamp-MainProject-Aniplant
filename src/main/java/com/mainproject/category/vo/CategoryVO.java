package com.mainproject.category.vo;

import java.sql.Timestamp;

public class CategoryVO {
	private int category_num;
	private int parent_category_num;
	private String name;
	private String read_permission;
	private String write_permission;
	private int created_user_num;
	private Timestamp created_at;
	private int updated_user_num;
	private Timestamp updated_at;
	private boolean is_deleted;
	private int deleted_user_num;
	private Timestamp deleted_at;
	
	public CategoryVO() {

	}
	public CategoryVO(String name) {
        this.name = name;
    }

	public int getCategory_num() {
		return category_num;
	}

	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}

	public int getParent_category_num() {
		return parent_category_num;
	}

	public void setParent_category_num(int parent_category_num) {
		this.parent_category_num = parent_category_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRead_permission() {
		return read_permission;
	}

	public void setRead_permission(String read_permission) {
		this.read_permission = read_permission;
	}

	public String getWrite_permission() {
		return write_permission;
	}

	public void setWrite_permission(String write_permission) {
		this.write_permission = write_permission;
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
