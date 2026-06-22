package com.pixcel.app.users.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pixcel.app.users.service.userServiceVO;

@Mapper
public interface userMapper {
	//회원가입
	public int insertUser(userServiceVO userVO);
	//아이디 중복 확인(회원가입시)
	public int checkLoginId(String loginId);
	
	//로그인
	public userServiceVO selectUserByLoginId(String loginId);
}
