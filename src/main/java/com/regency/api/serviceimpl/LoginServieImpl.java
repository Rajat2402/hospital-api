package com.regency.api.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.regency.api.configuration.JWTTokenProvider;
import com.regency.api.dto.AuthDto;
import com.regency.api.entity.Role;
import com.regency.api.entity.User;
import com.regency.api.exception.DoctorException;
import com.regency.api.repository.RoleRepository;
import com.regency.api.repository.UserRepository;
import com.regency.api.service.LoginService;

@Service
public class LoginServieImpl implements LoginService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	JWTTokenProvider jwtTokenProvider;

	@Override
	public String login(AuthDto authDto) {
		Authentication authenticate = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authDto.getUsernameoremail(), authDto.getPassword()));
		String token = jwtTokenProvider.generatedToken(authenticate);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return token;
	}

	@Override
	public String registerNewUser(User user) {

		// Check if users exist or not
		if (userRepo.existsByUsername(user.getUsername())) {
			throw new DoctorException(user.getUsername());
		} else {
			Role role = new Role();
			role.setName("ROLE_USER");
			Set<Role> roleSet = new HashSet<Role>();
			roleSet.add(role);
			user.setUserRole(roleSet);
			String password = user.getPassword();
			BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
			String encodedPassword = bcp.encode(password);
			user.setPassword(encodedPassword);

			userRepo.save(user);
			return "User " + user.getName() + " has been registered successfully";
		}
	}

}
