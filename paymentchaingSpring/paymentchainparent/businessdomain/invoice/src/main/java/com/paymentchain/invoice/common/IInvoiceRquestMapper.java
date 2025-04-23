/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.invoice.common;

import com.paymentchain.invoice.dto.InvoiceRequestDto;
import com.paymentchain.invoice.entities.Invoice;
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
public interface IInvoiceRquestMapper {
    @Mappings( {@Mapping(source = "customer", target = "customerId")})
    Invoice InvoiceRequestToInvoice(InvoiceRequestDto source);
    
    List<Invoice> InvoiceRequestListToInvoiceList(List<InvoiceRequestDto> source);
    
    @InheritInverseConfiguration
    InvoiceRequestDto InvoiceToInvoiceRequest(Invoice source);
    
    @InheritInverseConfiguration
    List<InvoiceRequestDto> InvoiceListToInvoiceRequestList(List<Invoice> source);
}
