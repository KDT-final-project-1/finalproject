package com.pixcel.app.user.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pixcel.app.users.mapper.userMapper;
import com.pixcel.app.users.service.userServiceVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final userMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        userServiceVO userVO = userMapper.selectUserByLoginId(loginId);

        if (userVO == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        
        return new CustomUserDetails(userVO);
    }
}