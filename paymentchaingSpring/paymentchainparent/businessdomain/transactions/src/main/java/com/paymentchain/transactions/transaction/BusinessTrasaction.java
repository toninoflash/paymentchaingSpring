/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transactions.transaction;

import com.paymentchain.transactions.common.ITransactionResponseMapper;
import com.paymentchain.transactions.common.ITransactionRquestMapper;
import com.paymentchain.transactions.dto.TransactionRequestDto;
import com.paymentchain.transactions.entities.Transaction;
import com.paymentchain.transactions.exception.BusinessRuleException;
import com.paymentchain.transactions.respository.TransactionRepository;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pc
 */
@Service
public class BusinessTrasaction {

    @Autowired
    TransactionRepository transactionRepository;
@Autowired
    ITransactionRquestMapper transactionRequestMapper;

    @Autowired
    ITransactionResponseMapper transactionResponseMapper;
    public Transaction create(TransactionRequestDto input) throws BusinessRuleException, UnknownHostException {
        Transaction transaction = transactionRequestMapper.TransactionRequestToTransaction(input);
        Transaction save = new Transaction();
        if (input.getIbanAccount().isBlank() || input.getReference().isBlank()) {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transaction sin IbanAccount o Reference. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        } else {
            save = totalAmountTransaction(transaction);
        }
        return save;
    }

    public Transaction update(String id, TransactionRequestDto input) throws BusinessRuleException {
        Optional<Transaction> findById = transactionRepository.findById(Long.valueOf(id));
        Transaction response = transactionResponseMapper.toOptional(findById);
        if (response != null) {
            response.setId(Long.valueOf(id));
            response.setAmount(input.getAmount());
            response.setChannel(input.getChannel());
            response.setDate(input.getDate());
            response.setDescription(input.getDescription());
            response.setFee(input.getFee());
            response.setIbanAccount(input.getIbanAccount());
            response.setReference(input.getReference());
            response.setStatus(input.getStatus());

        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
        Transaction save = transactionRepository.save(response);

        return save;
    }

    private Transaction totalAmountTransaction(Transaction input) throws BusinessRuleException {
        List<Transaction> transactions = transactionRepository.findByIbanAccount(input.getIbanAccount());
        Transaction save = new Transaction();
        long totalAmount = transactions.stream()
                .mapToLong(t -> (long) t.getAmount()) // convertir a long
                .sum();
        double newBalance = totalAmount + input.getAmount(); // input.getAmount() puede ser negativo

        if (input.getAmount() < 0 && newBalance <= 0) {
            // Si es un retiro (monto negativo) y dejaría el saldo en 0 o negativo
            throw new BusinessRuleException("0002", "Error creación. No es posible hacer retiros (operaciones con valor negativo) que dejen el saldo de la cuenta en 0.", HttpStatus.PRECONDITION_FAILED);
        } else {
            save = transactionRepository.save(calculateAndSetFeeAmount(input));
        }
        return save;
    }

    private Transaction calculateAndSetFeeAmount(Transaction input) {
        if (input.getAmount() < 0) {
            double fee = input.getAmount() * 0.0098;
            input.setFee(fee);
            input.setAmount(input.getAmount() + fee);
        }
        return input;
    }
}
