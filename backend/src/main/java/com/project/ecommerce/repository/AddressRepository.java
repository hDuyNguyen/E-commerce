package com.project.ecommerce.repository;

import com.project.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
