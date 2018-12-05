package com.anddigital.challenge.controller;

import com.anddigital.challenge.builder.CustomerBuilder;
import com.anddigital.challenge.builder.PhoneNumberBuilder;
import com.anddigital.challenge.builder.PhoneNumberSetBuilder;
import com.anddigital.challenge.domain.Customer;
import com.anddigital.challenge.domain.PhoneNumber;
import com.anddigital.challenge.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static com.anddigital.challenge.util.JsonMappingUtil.toJson;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerServiceMock;

    @Test
    public void getAllPhoneNumbers() throws Exception {
        PhoneNumber number1 = new PhoneNumberBuilder().withId(1L).withNumber("02071234568").withActiveStatus(false).build();
        PhoneNumber number2 = new PhoneNumberBuilder().withId(2L).withNumber("0207456789").withActiveStatus(false).build();
        PhoneNumber number3 = new PhoneNumberBuilder().withId(3L).withNumber("02074512345").withActiveStatus(true).build();

        Set<PhoneNumber> expectedNumbers = PhoneNumberSetBuilder.newBuilder()
                .withNewSet()
                .addPhoneNumber(number1)
                .addPhoneNumber(number2)
                .addPhoneNumber(number3)
                .build();

        when(customerServiceMock.getAllPhoneNumbers()).thenReturn(expectedNumbers);

        mockMvc.perform(get("/customers/numbers/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(expectedNumbers)));

        verify(customerServiceMock, only()).getAllPhoneNumbers();
    }

    @Test
    public void getPhoneNumbersForCustomer() throws Exception {
        Long customerId = 1L;

        PhoneNumber number1 = new PhoneNumberBuilder().withId(1L).withNumber("1234567890").withActiveStatus(false).build();
        PhoneNumber number2 = new PhoneNumberBuilder().withId(2L).withNumber("9876543210").withActiveStatus(false).build();
        PhoneNumber number3 = new PhoneNumberBuilder().withId(3L).withNumber("2233446688").withActiveStatus(true).build();

        Set<PhoneNumber> expectedNumbers = PhoneNumberSetBuilder.newBuilder()
                .withNewSet()
                .addPhoneNumber(number1)
                .addPhoneNumber(number2)
                .addPhoneNumber(number3)
                .build();

        when(customerServiceMock.getAllPhoneNumbersForCustomer(1L))
                .thenReturn(expectedNumbers);

        mockMvc.perform(get("/customers/{customerId}/numbers", customerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(expectedNumbers)));

        verify(customerServiceMock, only()).getAllPhoneNumbersForCustomer(customerId);
    }

    @Test
    public void activateNumberForCustomer() throws Exception {
        Long customerId = 1L;
        Long numberId = 2L;
        Boolean isActive = true;

        PhoneNumber number1 = new PhoneNumberBuilder().withId(1L).withNumber("1234567890").withActiveStatus(false).build();
        PhoneNumber number2 = new PhoneNumberBuilder().withId(2L).withNumber("9876543210").withActiveStatus(false).build();
        PhoneNumber number3 = new PhoneNumberBuilder().withId(3L).withNumber("2233446688").withActiveStatus(true).build();

        Set<PhoneNumber> customerNumbers = PhoneNumberSetBuilder.newBuilder()
                .withNewSet()
                .addPhoneNumber(number1)
                .addPhoneNumber(number2)
                .addPhoneNumber(number3)
                .build();

        Customer expectedCustomer = new CustomerBuilder()
                .withId(customerId)
                .withFirstName("Fred")
                .withLastName("Smith")
                .withPhoneNumbers(customerNumbers)
                .build();

        when(customerServiceMock.setPhoneNumberStatus(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyBoolean())).thenReturn(expectedCustomer);

        MvcResult result = mockMvc.perform(put("/customers/{customerId}/numbers/{numberId}/status/{isActive}", customerId, numberId, isActive).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(expectedCustomer)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        verify(customerServiceMock, only()).setPhoneNumberStatus(customerId, numberId, isActive);
   }
}