package com.project.ecommerce.repository;

import com.project.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
    public Users findByEmail(String email);
}
