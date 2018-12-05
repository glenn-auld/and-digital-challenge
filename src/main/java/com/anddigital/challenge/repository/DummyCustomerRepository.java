package com.anddigital.challenge.repository;

import com.anddigital.challenge.domain.Customer;
import com.anddigital.challenge.domain.PhoneNumber;
import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class is simply to roughly simulate repository calls for the purpose of the exercise (no database)
 */
@Component
public class DummyCustomerRepository {

    private static List<Customer> customerList = new ArrayList<>();

    static {
        PhoneNumber number1 = new PhoneNumber("1122334455", false);
        number1.setId(1L);
        PhoneNumber number2 = new PhoneNumber("2233445567", false);
        number2.setId(2L);
        PhoneNumber number3 = new PhoneNumber("4455678910", false);
        number3.setId(3L);
        PhoneNumber number4 = new PhoneNumber("8899007745", false);
        number4.setId(4L);
        PhoneNumber number5 = new PhoneNumber("6788991234", false);
        number5.setId(5L);
        PhoneNumber number6 = new PhoneNumber("3545452522", false);
        number6.setId(6L);
        PhoneNumber number7 = new PhoneNumber("9876554222", false);
        number7.setId(7L);
        PhoneNumber number8 = new PhoneNumber("7856229999", false);
        number8.setId(8L);

        ImmutableSet<PhoneNumber> numberList1 = ImmutableSet.of(number1, number2, number3);
        ImmutableSet<PhoneNumber> numberList2 = ImmutableSet.of(number4, number5);
        ImmutableSet<PhoneNumber> numberList3 = ImmutableSet.of(number6, number7);
        ImmutableSet<PhoneNumber> numberList4 = ImmutableSet.of(number8);

        Customer customer1 = new Customer("Fred", "Smith", numberList1);
        customer1.setId(1L);
        Customer customer2 = new Customer("Joe", "Brown", numberList2);
        customer1.setId(2L);
        Customer customer3 = new Customer("Jane", "Smith", numberList3);
        customer1.setId(3L);
        Customer customer4 = new Customer("Peter", "Jones", numberList4);
        customer1.setId(4L);

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
    }


    public List<Customer> findAll() {
        return customerList;
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerList.get(id.intValue()));
    }

    public Optional<Customer> findByIdAndPhoneNumberId(Long customerId, Long phoneNumberId) {
        return Optional.ofNullable(customerList.get(customerId.intValue()));
    }
}
