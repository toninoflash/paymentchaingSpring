/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.transactions.controller;

import com.paymentchain.transactions.common.ITransactionResponseMapper;
import com.paymentchain.transactions.common.ITransactionRquestMapper;
import com.paymentchain.transactions.dto.TransactionRequestDto;
import com.paymentchain.transactions.dto.TransactionResponseDto;
import com.paymentchain.transactions.entities.Transaction;
import com.paymentchain.transactions.exception.BusinessRuleException;
import com.paymentchain.transactions.respository.TransactionRepository;
import com.paymentchain.transactions.transaction.BusinessTrasaction;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BusinessTrasaction businessTrasaction;

    @Autowired
    ITransactionRquestMapper transactionRequestMapper;

    @Autowired
    ITransactionResponseMapper transactionResponseMapper;

    @GetMapping()
    public ResponseEntity<?> list() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponseDto> invoicesResp = transactionResponseMapper.TransactionListToTransactionResponseList(transactions);

        if (invoicesResp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin resultados");
        } else {
            return ResponseEntity.ok(invoicesResp);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        Transaction response = transactionResponseMapper.toOptional(transaction);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/customer/transactions")
    public ResponseEntity<?> get(@RequestParam(name = "ibanAccount") String ibanAccount) {
        List<Transaction> transactions = transactionRepository.findByIbanAccount(ibanAccount);
        List<TransactionResponseDto> transactionResp = transactionResponseMapper.TransactionListToTransactionResponseList(transactions);

        if (transactionResp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin resultados");
        } else {
            return ResponseEntity.ok(transactionResp);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") String id, @RequestBody TransactionRequestDto input) throws BusinessRuleException {
        Transaction find = businessTrasaction.update(id, input);
        return ResponseEntity.ok(find);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TransactionRequestDto input) throws BusinessRuleException, UnknownHostException {
        Transaction save = businessTrasaction.create(input);

        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Transaction> findById = transactionRepository.findById(id);
        Transaction response = transactionResponseMapper.toOptional(findById);

        if (response != null) {
            transactionRepository.delete(response);
        }
        return ResponseEntity.ok().build();
    }

}
