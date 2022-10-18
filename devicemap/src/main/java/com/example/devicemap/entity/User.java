package com.example.devicemap.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
@Table
public class User {
	@Id
	private String userId;
	
	private String username;
	
	private LocalDateTime createdDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", createdDate=" + createdDate + "]";
	}

	public User(String userId, String username, LocalDateTime createdDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.createdDate = createdDate;
	}

	public User() {
		super();
	}
	

}
