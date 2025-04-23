/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.invoice.controller;

import com.paymentchain.invoice.common.IInvoiceResponseMapper;
import com.paymentchain.invoice.common.IInvoiceRquestMapper;
import com.paymentchain.invoice.dto.InvoiceRequestDto;
import com.paymentchain.invoice.dto.InvoiceResponseDto;
import com.paymentchain.invoice.entities.Invoice;
import com.paymentchain.invoice.trasaction.BusinessTrasaction;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.paymentchain.invoice.respository.InvoiceRepository;
import org.springframework.http.HttpStatus;

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    BusinessTrasaction businessTrasaction;

    @Autowired
    IInvoiceRquestMapper invoiceRequestMapper;

    @Autowired
    IInvoiceResponseMapper invoiceResponseMapper;

    @GetMapping()
    public ResponseEntity<?> list() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceResponseDto> invoicesResp = invoiceResponseMapper.InvoiceListToInvoiceResponseList(invoices);
        if (invoicesResp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin resultados");
        } else {
            return ResponseEntity.ok(invoicesResp);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        Optional<Invoice> findById = invoiceRepository.findById(Long.valueOf(id));
        Invoice response = invoiceResponseMapper.toOptional(findById);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody InvoiceRequestDto input) {
        Optional<Invoice> findById = invoiceRepository.findById(Long.valueOf(id));
        Invoice response = invoiceResponseMapper.toOptional(findById);
         if(response != null){     
            response.setAmount(input.getAmount());
            response.setCustomerId(input.getCustomer());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody InvoiceRequestDto input) {
        Invoice invoice = invoiceRequestMapper.InvoiceRequestToInvoice(input);
        Invoice save = invoiceRepository.save(invoice);
        InvoiceResponseDto response = invoiceResponseMapper.InvoiceToInvoiceResponse(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Invoice> findById = invoiceRepository.findById(Long.valueOf(id));
        Invoice response = invoiceResponseMapper.toOptional(findById);
        if (response != null) {
            invoiceRepository.delete(response);
        }
        return ResponseEntity.ok(response);
    }

}
