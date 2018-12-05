package com.anddigital.challenge.builder;

import com.anddigital.challenge.domain.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

/**
 * Builder for sets of PhoneNumbers
 */
public class PhoneNumberSetBuilder {

    public static PhoneNumberSetBuilder newBuilder(){
        return new PhoneNumberSetBuilder();
    }

    private PhoneNumberSetBuilder() {}

    private Set<PhoneNumber> phoneNumberSet;

    public PhoneNumberSetBuilder withNewSet(){
        this.phoneNumberSet = new HashSet<>();
        return this;
    }

    public PhoneNumberSetBuilder withSet(Set phoneNumberSet){
        this.phoneNumberSet = phoneNumberSet;
        return this;
    }

    public PhoneNumberSetBuilder addPhoneNumber(PhoneNumber phoneNumber){
        this.phoneNumberSet.add(phoneNumber);
        return this;
    }

    public Set<PhoneNumber> build() {
        return this.phoneNumberSet;
    }
}
