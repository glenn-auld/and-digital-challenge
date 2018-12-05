package com.anddigital.challenge.builder;

import com.anddigital.challenge.domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for lists of Customers
 */
public class CustomerListBuilder {

    public static CustomerListBuilder newBuilder(){
        return new CustomerListBuilder();
    }

    private CustomerListBuilder() {}

    private List<Customer> customerList;

    public CustomerListBuilder withNewList(){
        this.customerList = new ArrayList<>();
        return this;
    }
    public CustomerListBuilder withList(List customerList){
        this.customerList = customerList;
        return this;
    }

    public CustomerListBuilder addCustomer(Customer Customer){
        this.customerList.add(Customer);
        return this;
    }

    public List<Customer> build(){
        return this.customerList;
    }
}
