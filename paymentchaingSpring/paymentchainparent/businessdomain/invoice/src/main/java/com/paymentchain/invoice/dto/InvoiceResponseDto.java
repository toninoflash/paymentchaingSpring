/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.invoice.dto;

import lombok.Data;

/**
 *
 * @author Pc
 */
@Data
public class InvoiceResponseDto {

    private long id;
    private long customer;
    private String number;
    private String detail;
    private double amount;
}
