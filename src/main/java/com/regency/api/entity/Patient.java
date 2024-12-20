package com.regency.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "patient")
@Table(name = "patient")
public class Patient {

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patient_id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "disease")
	private String disease;

	@CurrentTimestamp
	private Timestamp AddmissionDate;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public Patient() {
	}

	public Patient(int patient_id, String name, String address, String disease, Timestamp addmissionDate) {
		this.patient_id = patient_id;
		this.name = name;
		this.address = address;
		this.disease = disease;
		AddmissionDate = addmissionDate;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Timestamp getAddmissionDate() {
		return AddmissionDate;
	}

	public void setAddmissionDate(Timestamp addmissionDate) {
		AddmissionDate = addmissionDate;
	}

}
