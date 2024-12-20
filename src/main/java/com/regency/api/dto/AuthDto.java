package com.regency.api.dto;

public class AuthDto {

	private String usernameoremail;
	private String password;

	public AuthDto() {
	}

	public AuthDto(String usernameoremail, String password) {
		this.usernameoremail = usernameoremail;
		this.password = password;
	}

	public String getUsernameoremail() {
		return usernameoremail;
	}

	public void setUsernameoremail(String usernameoremail) {
		this.usernameoremail = usernameoremail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
