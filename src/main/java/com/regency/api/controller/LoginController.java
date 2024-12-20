package com.regency.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.regency.api.dto.AuthDto;
import com.regency.api.entity.User;
import com.regency.api.service.LoginService;


@RestController
@RequestMapping("/auth")
public class LoginController {


	@Autowired
	LoginService loginService;

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<String> loginWithUserNameOrEmail(@RequestBody AuthDto authDto) {
		return new ResponseEntity<String>(loginService.login(authDto), HttpStatus.OK);
	}
	
	@PostMapping(value = { "/signup", "/register" })
	public ResponseEntity<String> registerNewUser(@RequestBody User user) {
		return new ResponseEntity<String>(loginService.registerNewUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("testing", HttpStatus.OK);
	}
}
