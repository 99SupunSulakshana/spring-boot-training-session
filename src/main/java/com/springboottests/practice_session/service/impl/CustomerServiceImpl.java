package com.springboottests.practice_session.service.impl;

import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.request.CustomerUpdateDTO;
import com.springboottests.practice_session.entity.Customer;
import com.springboottests.practice_session.entity.Item;
import com.springboottests.practice_session.repo.CustomerRepo;
import com.springboottests.practice_session.service.CustomerService;
import com.springboottests.practice_session.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO,Customer.class);
        customer.setActive(true);
        if (customerRepo.existsByCustomerName(customer.getCustomerName())) {
            throw new RuntimeException("User with this name already exists!");
        }else {
            customerRepo.save(customer);
        }
        return customerDTO.toString();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsByCustomerId(customerUpdateDTO.getCustomerId())){
            Customer customer = customerRepo.getReferenceByCustomerId(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());
            customerRepo.save(customer);
            return customerUpdateDTO.getCustomerName() + " successfully updated!";
        }else{
            throw new RuntimeException("no data found!");
        }
    }

    @Override
    public CustomerDTO getCustomerById(String customerId) {
        if(customerRepo.existsByCustomerId(customerId)){
            Customer customer = customerRepo.getReferenceByCustomerId(customerId);
            //            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    customer.getContactNumber(),
//                    customer.getNic(),
//                    customer.isActive()
//            );
            return modelMapper.map(customer, CustomerDTO.class);
        } else {
            throw new RuntimeException("no data found!");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();


//        for(Customer customer: getAllCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    customer.getContactNumber(),
//                    customer.getNic(),
//                    customer.isActive()
//            );
//            customerDTOList.add(customerDTO);
//        }

        return itemMapper.entityCustomerListToDTOList(getAllCustomers);
    }

    @Override
    @Transactional
    public String deleteCustomer(String customerId) {
        if(customerRepo.existsByCustomerId(customerId)){
            customerRepo.deleteByCustomerId(customerId);
            return "deleted successfully " + customerId;
        }else{
            throw new RuntimeException("no data found!");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean status) {
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(status);
        //        for(Customer customer: getAllCustomers){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerId(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getCustomerSalary(),
//                    customer.getContactNumber(),
//                    customer.getNic(),
//                    customer.isActive()
//            );
//            customerDTOList.add(customerDTO);
//        }
        return itemMapper.entityCustomerListToDTOList(getAllCustomers);
    }

}
