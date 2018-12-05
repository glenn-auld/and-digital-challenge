package com.anddigital.challenge.builder;

import com.anddigital.challenge.domain.Customer;
import com.anddigital.challenge.domain.PhoneNumber;

import java.util.Set;

/**
 * Builder for Customer objects
 */
public class CustomerBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<PhoneNumber> phoneNumbers;

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder withPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer(firstName, lastName, phoneNumbers);
        customer.setId(id);
        return customer;
    }
}
