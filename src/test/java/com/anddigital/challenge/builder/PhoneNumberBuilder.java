package com.anddigital.challenge.builder;

import com.anddigital.challenge.domain.PhoneNumber;

/**
 * Builder for PhoneNumber objects
 */
public class PhoneNumberBuilder {
    private Long id;
    private String number;
    private Boolean isActive;

    public PhoneNumberBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PhoneNumberBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public PhoneNumberBuilder withActiveStatus(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public PhoneNumber build() {
        PhoneNumber phoneNumber = new PhoneNumber(number, isActive);
        phoneNumber.setId(id);
        return phoneNumber;
    }
}
