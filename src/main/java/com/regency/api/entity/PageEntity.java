package com.regency.api.entity;

import java.util.List;

import com.regency.api.dto.DoctorDto;

public class PageEntity {

	private int totalElement;		//done

	private int pageNo;				

	private int pageSize;			//done

	private int totalPage;				//done

	private boolean isLastPage;			//done

	private List<DoctorDto> doctor;			//done

	public PageEntity() {
	}

	public PageEntity(int pageNo, int pageSize, int totalPage, boolean isLastPage, List<DoctorDto> doctor,
			int totalElement) {
		this.doctor = doctor;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.isLastPage = isLastPage;
		this.totalElement = totalElement;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public List<DoctorDto> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<DoctorDto> doctor) {
		this.doctor = doctor;
	}

	public int getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(int totalElement) {
		this.totalElement = totalElement;
	}

}
