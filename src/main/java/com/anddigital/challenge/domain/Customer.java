package com.anddigital.challenge.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Set;

import javax.persistence.*;


/**
 * Represents a customer (simplified without address info, etc)
 * Each customer has N phone numbers.
 */
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    // A customer can have many numbers
    @OneToMany(fetch=FetchType.EAGER, mappedBy="customer")
    private Set<PhoneNumber> phoneNumbers;

    /**
     * Constructor with first name, last name and a collection of phone numbers
     *
     * @param firstName
     * @param lastName
     * @param phoneNumbers
     */
    public Customer(String firstName, String lastName, Set<PhoneNumber> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<PhoneNumber> getPhoneNumbers(){
        return phoneNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder()
                .append(id, customer.id)
                .append(firstName, customer.firstName)
                .append(lastName, customer.lastName)
                .append(phoneNumbers, customer.phoneNumbers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(firstName)
                .append(lastName)
                .append(phoneNumbers)
                .toHashCode();
    }
}
