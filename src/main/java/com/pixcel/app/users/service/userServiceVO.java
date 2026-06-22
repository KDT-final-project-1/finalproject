package com.pixcel.app.users.service;

import lombok.Data;

@Data
public class userServiceVO {
	private String userId;
	private String loginId;
	private String password;
	private String userName;
	private String email;
	private String phone;
	private String authYN;
	private String subscribeYN;
	
}
