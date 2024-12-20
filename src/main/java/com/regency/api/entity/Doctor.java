package com.regency.api.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "doctor")
@Entity(name = "doctor")
public class Doctor {

	@Id
	@Column(name = "doctor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctor_id;

	@Column(name = "name")
	private String name;

	@Column(name = "speciality")
	private String speciality;

	@Column(name = "experience")
	private int experience;

	@Column(name = "degree")
	private String degree;

	@OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL , orphanRemoval = true)
	private Set<Patient> patient;

	public Doctor() {
	}

	public Doctor(int doctor_id, String name, String speciality, int experience, String degree) {
		this.doctor_id = doctor_id;
		this.name = name;
		this.speciality = speciality;
		this.experience = experience;
		this.degree = degree;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
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

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

}
