package com.vmoscalciuc.customers.service;

import com.vmoscalciuc.customers.model.CustomerEntity;
import com.vmoscalciuc.customers.repository.CustomerRepository;
import com.vmoscalciuc.customers.dto.CustomerRequest;
import com.vmoscalciuc.customers.dto.CustomerResponse;
import com.vmoscalciuc.customers.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addCustomer(CustomerRequest customerRequest) {
        customerRepository.save(modelMapper.map(customerRequest, CustomerEntity.class));
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        return modelMapper.map(customerRepository.findById(customerId).orElseThrow(()
                -> new CustomerNotFoundException("No Customer with Id:" + customerId + " is found")),
                CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CustomerResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        CustomerEntity customer = customerRepository.findById(id).orElseThrow(()
                -> new CustomerNotFoundException("No Customer with Id:" + id + " is found"));
        modelMapper.map(customerRequest, customer);
        CustomerEntity updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerResponse.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("No Customer with Id:" + id + " is found");
        }
        customerRepository.deleteById(id);
    }
}
