package com.regency.api.service;

import com.regency.api.dto.AuthDto;
import com.regency.api.entity.User;

public interface LoginService {

	public String login(AuthDto authDto);

	public String registerNewUser(User user);

}
