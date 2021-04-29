package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByPets_Id(Long petId);
}
