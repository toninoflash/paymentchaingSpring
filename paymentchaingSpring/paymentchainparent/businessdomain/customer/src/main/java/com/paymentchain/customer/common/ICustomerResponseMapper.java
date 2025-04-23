/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.customer.common;

import com.paymentchain.customer.dto.CustomerResponseDto;
import com.paymentchain.customer.entities.Customer;
import java.util.List;
import java.util.Optional;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface ICustomerResponseMapper {
    
    Customer CustomerResponseToCustomer(CustomerResponseDto source);
    
    List<Customer> CustomerResponseListToCustomerList(List<CustomerResponseDto> source);
    
    @InheritInverseConfiguration
    CustomerResponseDto CustomerToCustomerResponse(Customer source);
    
    @InheritInverseConfiguration
    List<CustomerResponseDto> CustomerListToCustomerResponseList(List<Customer> source);
    
    Customer toOptional (Optional<Customer> source);
}
