package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.user.UserDao;
import com.example.demo.user.Users;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users u = dao.findById(username).orElseThrow(
				() -> new UsernameNotFoundException("not found id:" + username));
		System.out.println("security service: " + u);
		return new MyUserDetails(u);
	}

}
