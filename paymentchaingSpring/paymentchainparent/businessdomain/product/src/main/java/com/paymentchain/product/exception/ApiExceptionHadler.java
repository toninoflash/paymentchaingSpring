/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.exception;

import com.paymentchain.product.common.StandarizedApiExceptionResponseProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Pc
 */
@RestControllerAdvice
public class ApiExceptionHadler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> hadlerUnknownHostException(Exception ex) {
        StandarizedApiExceptionResponseProduct standarizedApiExeptionResponse = new StandarizedApiExceptionResponseProduct("Tecnico", "Input Ouput error", "0001", ex.getMessage(), ex.getLocalizedMessage());
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standarizedApiExeptionResponse);
    }
    
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<?> hadlerBusinessRuleException(BusinessRuleException ex) {
        StandarizedApiExceptionResponseProduct standarizedApiExeptionResponse = new StandarizedApiExceptionResponseProduct("Business", "Error", ex.getCode(), ex.getMessage(), ex.getLocalizedMessage());
        return  ResponseEntity.status(ex.getHttpStatus()).body(standarizedApiExeptionResponse);
    }
}
