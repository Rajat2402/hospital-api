package com.regency.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.regency.api.dto.DoctorDto;
import com.regency.api.entity.PageEntity;
import com.regency.api.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctor")
@Tag(name = "Doctor Controller", description = "This controller can process with doctor data only")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@GetMapping("/fetchall")
	public ResponseEntity<PageEntity> fetchAllDoctor(
			@RequestParam(name = "pageno", defaultValue = "0", required = false) int pageno,
			@RequestParam(name = "pagesize", required = false, defaultValue = "10") int pagesize,
			@RequestParam(name = "sortdir", required = false, defaultValue = "ASC") String sortdir) {
		return new ResponseEntity<PageEntity>(doctorService.fetchAllDoctor(pageno, pagesize, sortdir), HttpStatus.OK);
	}

	@Operation(summary = "Get doctor by his or her Id")
	@GetMapping("/fetch/{doctor_id}")
	public ResponseEntity<DoctorDto> fetchDoctor(@PathVariable(name = "doctor_id") int doctor_id) {
		return new ResponseEntity<DoctorDto>(doctorService.fetchDoctor(doctor_id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<DoctorDto> registerDoctor(@Valid @RequestBody DoctorDto doctorDto) {
		return new ResponseEntity<DoctorDto>(doctorService.registerDoctor(doctorDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update{doctor_id}")
	public ResponseEntity<DoctorDto> registerDoctor(@Valid @RequestBody DoctorDto doctorDto,
			@PathVariable("doctor_id") int doctor_id) {
		return new ResponseEntity<DoctorDto>(doctorService.updateDoctor(doctorDto, doctor_id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{doctor_id}")
	public ResponseEntity<DoctorDto> deleteDoctor(@PathVariable("doctor_id") int doctor_id) {
		return new ResponseEntity<DoctorDto>(doctorService.deleteDoctor(doctor_id), HttpStatus.OK);
	}

}
