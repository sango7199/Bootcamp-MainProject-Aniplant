package com.mainproject.user.service;

import com.mainproject.user.dao.UserDAO;
import com.mainproject.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO user = userDAO.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("ADMIN".equals(user.getIs_admin())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if ("PRIVACY_ADMIN".equals(user.getIs_admin())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PRIVACY_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPwd(), authorities);
    }
}
