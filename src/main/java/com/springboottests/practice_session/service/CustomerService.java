package com.springboottests.practice_session.service;

import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);

    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    public CustomerDTO getCustomerById(String customerId);

    public List<CustomerDTO> getAllCustomers();

    public String deleteCustomer(String customerId);

    public List<CustomerDTO> getAllCustomersByActiveState(boolean status);
}
