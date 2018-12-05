package com.anddigital.challenge.service;

import com.anddigital.challenge.Exception.ResourceNotFoundException;
import com.anddigital.challenge.builder.CustomerBuilder;
import com.anddigital.challenge.builder.CustomerListBuilder;
import com.anddigital.challenge.builder.PhoneNumberBuilder;
import com.anddigital.challenge.builder.PhoneNumberSetBuilder;
import com.anddigital.challenge.domain.Customer;
import com.anddigital.challenge.domain.PhoneNumber;
import com.anddigital.challenge.repository.DummyCustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Mock
    DummyCustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void getAllPhoneNumbers() {

        Long[] customerIds = new Long[] {1L, 2L, 3L};
        PhoneNumber number1 = new PhoneNumberBuilder().withId(1L).withNumber("1234567890").withActiveStatus(false).build();
        PhoneNumber number2 = new PhoneNumberBuilder().withId(2L).withNumber("1122334455").withActiveStatus(false).build();
        PhoneNumber number3 = new PhoneNumberBuilder().withId(3L).withNumber("6677889900").withActiveStatus(false).build();

        Set<PhoneNumber> customerNumbers1 = PhoneNumberSetBuilder.newBuilder().withNewSet().addPhoneNumber(number1).build();
        Set<PhoneNumber> customerNumbers2 = PhoneNumberSetBuilder.newBuilder().withNewSet().addPhoneNumber(number2).build();
        Set<PhoneNumber> customerNumbers3 = PhoneNumberSetBuilder.newBuilder().withNewSet().addPhoneNumber(number3).build();
        Set<PhoneNumber> expected = new HashSet<>(0);
        expected.addAll(customerNumbers1);
        expected.addAll(customerNumbers2);
        expected.addAll(customerNumbers3);

        Customer customer1 = new CustomerBuilder()
                .withId(customerIds[0])
                .withFirstName("Fred")
                .withLastName("Smith")
                .withPhoneNumbers(customerNumbers1)
                .build();

        Customer customer2 = new CustomerBuilder()
                .withId(customerIds[1])
                .withFirstName("Joe")
                .withLastName("Brown")
                .withPhoneNumbers(customerNumbers2)
                .build();

        Customer customer3 = new CustomerBuilder()
                .withId(customerIds[2])
                .withFirstName("Jane")
                .withLastName("Doe")
                .withPhoneNumbers(customerNumbers3)
                .build();

        List<Customer> customers = CustomerListBuilder.newBuilder()
                .withNewList()
                .addCustomer(customer1)
                .addCustomer(customer2)
                .addCustomer(customer3)
                .build();

        when(customerRepository.findAll()).thenReturn(customers);

        Set<PhoneNumber> result = customerService.getAllPhoneNumbers();
        assertEquals(expected, result);
    }

    @Test
    public void getPhoneNumbersForCustomer() throws Exception {
        PhoneNumber number1 = new PhoneNumberBuilder().withId(1L).withNumber("1234567890").withActiveStatus(false).build();
        PhoneNumber number2 = new PhoneNumberBuilder().withId(2L).withNumber("1122334455").withActiveStatus(false).build();
        PhoneNumber number3 = new PhoneNumberBuilder().withId(3L).withNumber("6677889900").withActiveStatus(false).build();

        Set<PhoneNumber> customerNumbers = PhoneNumberSetBuilder.newBuilder()
                .withNewSet()
                .addPhoneNumber(number1)
                .addPhoneNumber(number2)
                .addPhoneNumber(number3)
                .build();

        Customer customer = new CustomerBuilder()
                .withId(1L)
                .withFirstName("Jane")
                .withLastName("Doe")
                .withPhoneNumbers(customerNumbers)
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Set<PhoneNumber> result = customerService.getAllPhoneNumbersForCustomer(1L);
        assertEquals(customerNumbers, result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getPhoneNumbersForCustomer_throwsExceptionForCustomerNotFound() throws Exception {
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());
        Set<PhoneNumber> result = customerService.getAllPhoneNumbersForCustomer(2L);
    }

    @Test
    public void activateNumberForCustomer() throws Exception {
        PhoneNumber number1 = new PhoneNumberBuilder().withId(1L).withNumber("1234567890").withActiveStatus(false).build();
        PhoneNumber number2 = new PhoneNumberBuilder().withId(2L).withNumber("1122334455").withActiveStatus(false).build();
        PhoneNumber number3 = new PhoneNumberBuilder().withId(3L).withNumber("6677889900").withActiveStatus(false).build();

        Set<PhoneNumber> customerNumbers = PhoneNumberSetBuilder.newBuilder()
                .withNewSet()
                .addPhoneNumber(number1)
                .addPhoneNumber(number2)
                .addPhoneNumber(number3)
                .build();

        Customer customer = new CustomerBuilder()
                .withId(1L)
                .withFirstName("Jane")
                .withLastName("Doe")
                .withPhoneNumbers(customerNumbers)
                .build();

        when(customerRepository.findByIdAndPhoneNumberId(1L, 2L)).thenReturn(Optional.of(customer));
        Customer updatedCustomer = customerService.setPhoneNumberStatus(1L, 2L, true);
        Set<PhoneNumber> updatedCustomerNumbers = updatedCustomer.getPhoneNumbers();
        PhoneNumber updatedNumber = updatedCustomerNumbers.stream().filter(num -> num.getId().equals(2L)).findAny().orElse(null);

        assertTrue(updatedNumber.isActive());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void activateNumberForCustomer_throwsExceptionForNumberNotFound() throws Exception {
        when(customerRepository.findByIdAndPhoneNumberId(1L, 2L)).thenReturn(Optional.empty());
        Customer updatedCustomer = customerService.setPhoneNumberStatus(1L, 2L, true);
    }
}
