/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.invoice.common;

import com.paymentchain.invoice.dto.InvoiceResponseDto;
import com.paymentchain.invoice.entities.Invoice;
import java.util.List;
import java.util.Optional;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface IInvoiceResponseMapper {
    @Mappings( {@Mapping(source = "customer", target = "customerId")})
    Invoice InvoiceResponseToInvoice(InvoiceResponseDto source);
    
    List<Invoice> InvoiceResponseListToInvoiceList(List<InvoiceResponseDto> source);
    
    @InheritInverseConfiguration
    InvoiceResponseDto InvoiceToInvoiceResponse(Invoice source);
    
    @InheritInverseConfiguration
    List<InvoiceResponseDto> InvoiceListToInvoiceResponseList(List<Invoice> source);
    
    Invoice toOptional (Optional<Invoice> source);
}
