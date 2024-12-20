package com.regency.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.regency.api.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
