package com.pixcel.app.user.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pixcel.app.users.service.userServiceVO;



public class CustomUserDetails implements UserDetails {
	
	private final userServiceVO userVO;
	
    public CustomUserDetails(userServiceVO userVO) {
        this.userVO = userVO;
    }

    public String getUserId() {
        return userVO.getUserId();
    }

    public String getLoginId() {
        return userVO.getLoginId();
    }

    public String getUserName() {
        return userVO.getUserName();
    }

    public String getAuthYn() {
        return userVO.getAuthYN();
    }

    public String getSubscribeYn() {
        return userVO.getSubscribeYN();
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}
	
	@Override
	public String getUsername() {
		return userVO.getUserId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	} 
	
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
