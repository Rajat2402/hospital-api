package com.regency.api.serviceimpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.regency.api.repository.UserRepository;

@Component
public class UserDetialsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {

		com.regency.api.entity.User user = userRepository.findByUsernameOrEmail(usernameoremail, usernameoremail)
				.orElseThrow(() -> new BadCredentialsException("You have entered bad credentials"));

		Set<SimpleGrantedAuthority> set = new HashSet<SimpleGrantedAuthority>();
		set = user.getUserRole().stream().map(s -> new SimpleGrantedAuthority(s.getName())).collect(Collectors.toSet());

		UserDetails userDetails = User.builder().username(user.getUsername()).password(user.getPassword())
				.authorities(set).build();

		return userDetails;
	}

}
