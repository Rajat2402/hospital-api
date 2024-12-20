package com.regency.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.regency.api.dto.DoctorDto;
import com.regency.api.entity.Doctor;
import com.regency.api.entity.PageEntity;
import com.regency.api.exception.DoctorException;
import com.regency.api.repository.DoctorRepository;
import com.regency.api.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public PageEntity fetchAllDoctor(int pageno, int pagesize, String sortdir) {
		PageEntity pageEntity = new PageEntity();
		Pageable page = null;

		if (sortdir != null && sortdir.equals("ASC")) {
			page = PageRequest.of(pageno, pagesize, Sort.by(Direction.ASC, "name"));
		} else {
			page = PageRequest.of(pageno, pagesize, Sort.by(Direction.DESC, "name"));
		}
		Page<Doctor> doctorPage = doctorRepository.findAll(page);
		pageEntity.setPageNo(doctorPage.getNumber());
		pageEntity.setPageSize(doctorPage.getSize());
		pageEntity.setTotalElement((int) doctorPage.getTotalElements());
		pageEntity.setTotalPage(doctorPage.getTotalPages());
		List<Doctor> listOfDoctor = doctorPage.getContent();
		boolean isLastPage = doctorPage.isLast();
		pageEntity.setLastPage(isLastPage);
		List<DoctorDto> listOfDocDto = new ArrayList<DoctorDto>();
		listOfDoctor.forEach(li -> {
			listOfDocDto.add(modelMapper.map(li, DoctorDto.class));
		});
		pageEntity.setDoctor(listOfDocDto);
		return pageEntity;
	}

	@Override
	public DoctorDto registerDoctor(DoctorDto doctorDto) {
		Doctor doctor = doctorRepository.save(modelMapper.map(doctorDto, Doctor.class));
		return modelMapper.map(doctor, DoctorDto.class);
	}

	@Override
	public DoctorDto updateDoctor(DoctorDto doctorDto, int doctor_id) {
		if (doctorRepository.existsById(doctor_id)) {
			Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
			doctor.setDoctor_id(doctor_id);
			Doctor savedDoctor = doctorRepository.save(doctor);
			return modelMapper.map(savedDoctor, DoctorDto.class);
		} else {
			throw new DoctorException("Doctor", doctor_id);
		}

	}

	@Override
	public DoctorDto deleteDoctor(int doctor_id) {
		// Check id is exist or not
		boolean doctorExist = doctorRepository.existsById(doctor_id);
		// fetch doctor for return
		Optional<Doctor> findById = null;
		if (doctorExist) {
			findById = doctorRepository.findById(doctor_id);
			doctorRepository.deleteById(doctor_id);
		} else {
			throw new DoctorException("Doctor", doctor_id);
		}
		return modelMapper.map(findById.get(), DoctorDto.class);
	}

	@Override
	public DoctorDto fetchDoctor(int doctor_id) {
		Doctor doctor = doctorRepository.findById(doctor_id)
				.orElseThrow(() -> new DoctorException("Doctor", doctor_id));
		return modelMapper.map(doctor, DoctorDto.class);
	}

}
