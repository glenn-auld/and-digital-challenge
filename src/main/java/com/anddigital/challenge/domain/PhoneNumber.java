package com.anddigital.challenge.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Represents a phone number and its state (active/inactive)
 */
@Entity
@Table(name="phone_number")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "customer_id", nullable=false)
    private Long customerId;

    // One customer can have many numbers
    @ManyToOne(optional=false)
    @JoinColumn(name="customer_id",referencedColumnName="customer_id", insertable=false, updatable=false)
    private Customer customer;

    @Column(name = "number")
    private String number;

    @Column(name = "is_active")
    private boolean isActive;

    public PhoneNumber() {};

    public PhoneNumber(String number, boolean isActive) {
        super();
        setNumber(number);
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void activate() {
        this.isActive = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return new EqualsBuilder()
                .append(isActive, that.isActive)
                .append(id, that.id)
                .append(customerId, that.customerId)
                .append(customer, that.customer)
                .append(number, that.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(customerId)
                .append(customer)
                .append(number)
                .append(isActive)
                .toHashCode();
    }
}
