package com.regency.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.regency.api.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
