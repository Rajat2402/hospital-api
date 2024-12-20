package com.regency.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DoctorDto {

	private int doctor_id;

	@NotEmpty(message = "Name should not be empty")
	@NotNull(message = "Name should be not null")
	private String name;

	@NotEmpty(message = "speciality should not be empty")
	@NotNull(message = "speciality should be not null")
	private String speciality;

	@Min(value = 30, message = "Minimum experience age  of dr should be 30")
	private int experience;

	@NotEmpty(message = "degree should not be empty")
	@NotNull(message = "degree should be not null")
	private String degree;

	public DoctorDto() {
	}

	public DoctorDto(String name, String speciality, int experience, String degree, int doctor_id) {
		this.name = name;
		this.speciality = speciality;
		this.experience = experience;
		this.degree = degree;
		this.doctor_id = doctor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

}
