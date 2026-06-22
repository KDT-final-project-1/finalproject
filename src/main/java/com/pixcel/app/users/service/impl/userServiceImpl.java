package com.pixcel.app.users.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pixcel.app.users.mapper.userMapper;
import com.pixcel.app.users.service.userService;
import com.pixcel.app.users.service.userServiceVO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class userServiceImpl implements userService {
	
	public final userMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	//회원가입
	@Override
	@Transactional
	public Map<String, Object> joinUser(userServiceVO userVO) {
		Map<String, Object> resultMap = new HashMap<>();
		
		//회원가입 시 한번더 아이디 중복 체크를 진행한다.
		int count = userMapper.checkLoginId(userVO.getLoginId());
		if(count > 0) {
			resultMap.put("result",false);
			resultMap.put("message","이미 사용중인 아이디입니다.");
			return resultMap;
		}
		
		// 비밀번호 암호화
		String encodedPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encodedPassword);
		
		//Insert
		int result = userMapper.insertUser(userVO);
		
		if(result > 0) {
			resultMap.put("result",true);
			resultMap.put("message", "회원가입이 완료되었습니다.");
			resultMap.put("userId", userVO.getUserId());
		}else {
			resultMap.put("result", false);
			resultMap.put("message", "회원가입이 실패하였습니다.");
		}
		return resultMap;
	}

	//아이디 중복확인
	@Override
	public boolean checkLoginId(String loginId) {
		int count = userMapper.checkLoginId(loginId);
		return count > 0;
	}

}
