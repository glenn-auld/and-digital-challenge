package com.anddigital.challenge.service;

import com.anddigital.challenge.Exception.ResourceNotFoundException;
import com.anddigital.challenge.domain.Customer;
import com.anddigital.challenge.domain.PhoneNumber;
import com.anddigital.challenge.repository.CustomerRepository;
import com.anddigital.challenge.repository.DummyCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;


/**
 * Service layer for the API/controller layer
 */
@Service
public class CustomerService {

    @Autowired
    DummyCustomerRepository customerRepository;

    public Set<PhoneNumber> getAllPhoneNumbers() {
        List<Customer> customers = customerRepository.findAll();

        return customers
                .stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .collect(Collectors.toSet());
    }

    public Set<PhoneNumber> getAllPhoneNumbersForCustomer(Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Customer %s not found", customerId)));

        return customer.getPhoneNumbers();
    }

    public Customer setPhoneNumberStatus(Long customerId, Long phoneNumberId, Boolean isActivated) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByIdAndPhoneNumberId(customerId, phoneNumberId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Customer with Id %s not found", customerId)));

        PhoneNumber phoneNumber = customer.getPhoneNumbers()
                .stream()
                .filter(number -> number.getId().equals(phoneNumberId))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException(format("Phone number with Id %s not found", phoneNumberId)));

        phoneNumber.setIsActive(isActivated);
        return customer;
    }
}
