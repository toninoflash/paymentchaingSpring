/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.invoice.entities;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 *
 * @author sotobotero
 */
@Entity
@Data
public class Invoice {
   @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
   private long customerId;
   private String number;
   private String detail;
   private double amount;  
   
}
