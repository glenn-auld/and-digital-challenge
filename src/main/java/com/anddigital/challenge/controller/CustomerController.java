package com.anddigital.challenge.controller;


import com.anddigital.challenge.Exception.ResourceNotFoundException;
import com.anddigital.challenge.domain.Customer;
import com.anddigital.challenge.domain.PhoneNumber;
import com.anddigital.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * CustomerController exposes Customer data and operations through a RESTful API.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/numbers")
    public Set<PhoneNumber> getAllPhoneNumbers() {

        return customerService.getAllPhoneNumbers();
    }

    @GetMapping("/{customerId}/numbers")
    public Set<PhoneNumber> getAllPhoneNumbersForCustomer(@PathVariable Long customerId) throws ResourceNotFoundException {

        return customerService.getAllPhoneNumbersForCustomer(customerId);
    }

    @PutMapping("/{customerId}/numbers/{numberId}/status/{isActivated}")
    public Customer setPhoneNumberStatus (
            @PathVariable Long customerId,
            @PathVariable Long numberId,
            @PathVariable Boolean isActivated) throws ResourceNotFoundException  {

        return customerService.setPhoneNumberStatus(customerId, numberId, isActivated);
    }
}
