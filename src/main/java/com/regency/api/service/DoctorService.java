package com.regency.api.service;


import com.regency.api.dto.DoctorDto;
import com.regency.api.entity.PageEntity;

public interface DoctorService {

	PageEntity fetchAllDoctor(int pageno , int pagesize , String sotddir);

	DoctorDto registerDoctor(DoctorDto doctorDto);

	DoctorDto updateDoctor(DoctorDto doctorDto, int doctor_id);

	DoctorDto deleteDoctor(int doctor_id);

	DoctorDto fetchDoctor(int doctor_id);

}
