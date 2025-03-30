package com.springboottests.practice_session.controller;

import com.springboottests.practice_session.dto.CustomerDTO;
import com.springboottests.practice_session.dto.request.CustomerUpdateDTO;
import com.springboottests.practice_session.entity.Customer;
import com.springboottests.practice_session.service.CustomerService;
import com.springboottests.practice_session.util.exceptions.NotFoundException;
import com.springboottests.practice_session.util.response.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@Tag(name = "Customer API", description = "Operations related to customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "save customer")
    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
        String result = customerService.saveCustomer(customerDTO);
        return result;
    }

    @Operation(summary = "update customer details")
    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String result = customerService.updateCustomer(customerUpdateDTO);
        return result;
    }

    @Operation(summary = "get customer by id")
    @GetMapping(path = "/get-by-id", params = "id")
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") String customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        if(customerDTO != null){
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(
                            200,
                            "Successfully!",
                            customerDTO
                    ),
                    HttpStatus.OK
            );
        }else{
            throw new NotFoundException("No Customers Found!");
        }
    }

    @Operation(summary = "get all customers")
    @GetMapping(path = "/get-all-customers")
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        if(!allCustomers.isEmpty()){
            return allCustomers;
        }else {
            throw new NotFoundException("No Customers Found!");
        }
    }

    @Operation(summary = "delete customer")
    @DeleteMapping(path = "/delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") String customerId) {
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;
    }

    @Operation(summary = "get all customers by active state")
    @GetMapping(path = "/get-all-customers-by-active-state/{status}")
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "status") boolean status) {
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(status);
        return allCustomers;
    }
}
