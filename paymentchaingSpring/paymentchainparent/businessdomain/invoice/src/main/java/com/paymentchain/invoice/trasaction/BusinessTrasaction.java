/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.invoice.trasaction;

import com.paymentchain.invoice.entities.Invoice;
import com.paymentchain.invoice.exception.BusinessRuleException;
import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paymentchain.invoice.respository.InvoiceRepository;

/**
 *
 * @author Pc
 */
@Service
public class BusinessTrasaction {

    @Autowired
    InvoiceRepository invoiceRepository;

    public Invoice create(Invoice input) throws BusinessRuleException, UnknownHostException {
        
        Invoice save = invoiceRepository.save(input);
        return save;
    }
    
}
