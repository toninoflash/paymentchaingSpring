/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.paymentchain.customer.common.ICustomerResponseMapper;
import com.paymentchain.customer.common.ICustomerRquestMapper;
import com.paymentchain.customer.dto.CustomerRequestDto;
import com.paymentchain.customer.dto.CustomerResponseDto;
import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.exception.BusinessRuleException;
import com.paymentchain.customer.respository.CustomerRepository;
import com.paymentchain.customer.trasaction.BusinessTrasaction;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BusinessTrasaction businessTrasaction;

    @Autowired
    ICustomerRquestMapper customerRequestMapper;

    @Autowired
    ICustomerResponseMapper customerResponseMapper;
    //@Value("${custom.activeprofile}")
    // private String profile;
    @Autowired
    private Environment env;

    @GetMapping("/check")
    public String get() {
        return "Hellow a Check. Your property value is: " + env.getProperty("custom.activeprofileName");
    }

    @GetMapping()
    public ResponseEntity<?> list() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customersResp = customerResponseMapper.CustomerListToCustomerResponseList(customers);
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin resultados");
        } else {
            return ResponseEntity.ok(customersResp);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Customer> opt = customerRepository.findById(id);
        Customer customer = customerResponseMapper.toOptional(opt);
        if (opt.isPresent()) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") String id, @RequestBody CustomerRequestDto input) {
        Optional<Customer> findById = customerRepository.findById(Long.valueOf(id));
        Customer response = customerResponseMapper.toOptional(findById);
        Customer customerInput = customerRequestMapper.CustomerRequestToCustomer(input);
        if (response != null) {
            response.setCode(customerInput.getCode());
            response.setName(customerInput.getName());
            response.setIban(customerInput.getIban());
            response.setPhone(customerInput.getPhone());
            response.setSurname(customerInput.getSurname());
            response.setProducts(customerInput.getProducts());

        }
        Customer save = customerRepository.save(response);
        CustomerResponseDto responseDto = customerResponseMapper.CustomerToCustomerResponse(save);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CustomerRequestDto input) throws BusinessRuleException, UnknownHostException {
        Customer customerInput = customerRequestMapper.CustomerRequestToCustomer(input);
        Customer save = businessTrasaction.create(customerInput);
        CustomerResponseDto responseDto = customerResponseMapper.CustomerToCustomerResponse(save);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Customer> findById = customerRepository.findById(id);
        if (findById.get() != null) {
            customerRepository.delete(findById.get());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/full")
    public ResponseEntity<?> getByCode(@RequestParam(name = "code") String code) {
        Customer save = businessTrasaction.getByCode(code);
        CustomerResponseDto responseDto = customerResponseMapper.CustomerToCustomerResponse(save);

        if (save != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
