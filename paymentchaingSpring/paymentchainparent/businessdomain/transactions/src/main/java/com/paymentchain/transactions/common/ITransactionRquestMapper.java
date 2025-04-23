/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.transactions.common;

import com.paymentchain.transactions.dto.TransactionRequestDto;
import com.paymentchain.transactions.entities.Transaction;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface ITransactionRquestMapper {
    Transaction TransactionRequestToTransaction(TransactionRequestDto source);
    
    List<Transaction> TransactionRequestListToTransactionList(List<TransactionRequestDto> source);
    
    @InheritInverseConfiguration
    TransactionRequestDto TransactionToTransactionRequest(Transaction source);
    
    @InheritInverseConfiguration
    List<TransactionRequestDto> TransactionListToTransactionRequestList(List<Transaction> source);
}
