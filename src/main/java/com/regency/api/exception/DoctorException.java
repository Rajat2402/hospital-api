package com.regency.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, code = HttpStatus.BAD_GATEWAY)
public class DoctorException extends RuntimeException {

	HttpStatus status;
	private int doctor_id;
	private String doctor;

	public DoctorException(String doctor, int doctor_id) {

		super(String.format("%s not found with id %s", doctor, doctor_id));
		this.doctor = doctor;
		this.doctor_id = doctor_id;
	}

	public DoctorException(String username) {
		super(String.format("%s not found with in our records", username));
	}

	public DoctorException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
