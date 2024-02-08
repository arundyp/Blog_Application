package com.arun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
