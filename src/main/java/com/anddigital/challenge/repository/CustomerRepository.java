package com.anddigital.challenge.repository;

import com.anddigital.challenge.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Standard JPA repository (for indicative purposes only)
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdAndPhoneNumberId(Long customerId, Long phoneNumberId);
}
