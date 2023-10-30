package com.vmoscalciuc.customers.service;

import com.vmoscalciuc.customers.dto.CustomerRequest;
import com.vmoscalciuc.customers.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    void addCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomerById(Long customerId);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);
    void deleteCustomer(Long id);
}
