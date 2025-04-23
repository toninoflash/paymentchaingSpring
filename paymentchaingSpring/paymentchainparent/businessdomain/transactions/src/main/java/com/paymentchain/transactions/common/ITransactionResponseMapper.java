/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.transactions.common;

import com.paymentchain.transactions.dto.TransactionResponseDto;
import com.paymentchain.transactions.entities.Transaction;
import java.util.List;
import java.util.Optional;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface ITransactionResponseMapper {
    Transaction TransactionResponseToTransaction(TransactionResponseDto source);
    
    List<Transaction> TransactionResponseListToTransactionList(List<TransactionResponseDto> source);
    
    @InheritInverseConfiguration
    TransactionResponseDto TransactionToTransactionResponse(Transaction source);
    
    @InheritInverseConfiguration
    List<TransactionResponseDto> TransactionListToTransactionResponseList(List<Transaction> source);
    
    Transaction toOptional (Optional<Transaction> source);
}
