package com.pixcel.app.users.service;

import java.util.Map;

public interface userService {

	//회원가입
	public Map<String, Object> joinUser(userServiceVO userVO);
	
	//아이디 중복확인
	public boolean checkLoginId(String loginId);
}
