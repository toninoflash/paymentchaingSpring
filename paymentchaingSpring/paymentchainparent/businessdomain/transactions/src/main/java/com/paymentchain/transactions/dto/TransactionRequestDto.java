/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transactions.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Pc
 */
@Data
public class TransactionRequestDto {

   private String reference;
   private String ibanAccount;
   private LocalDateTime date;
   private double amount ;
   private double fee;   
   private String description;
   private String status;
   private String channel;   
}
