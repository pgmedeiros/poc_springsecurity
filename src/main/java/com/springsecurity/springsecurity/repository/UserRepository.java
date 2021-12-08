package com.springsecurity.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springsecurity.springsecurity.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
    Users findByUsername(String username);
}
