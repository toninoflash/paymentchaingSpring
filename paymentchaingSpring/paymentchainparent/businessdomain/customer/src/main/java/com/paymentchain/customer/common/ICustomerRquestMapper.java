/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.customer.common;

import com.paymentchain.customer.dto.CustomerRequestDto;
import com.paymentchain.customer.entities.Customer;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface ICustomerRquestMapper {
    Customer CustomerRequestToCustomer(CustomerRequestDto source);
    
    List<Customer> CustomerRequestListToCustomerList(List<CustomerRequestDto> source);
    
    @InheritInverseConfiguration
    CustomerRequestDto CustomerToCustomerRequest(Customer source);
    
    @InheritInverseConfiguration
    List<CustomerRequestDto> CustomerListToCustomerRequestList(List<Customer> source);
}
