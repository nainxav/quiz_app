package com.springboot.projekPBO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.projekPBO.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    // Custom methods for user-related operations
	public List<Users> findByUsername(String username);
}
