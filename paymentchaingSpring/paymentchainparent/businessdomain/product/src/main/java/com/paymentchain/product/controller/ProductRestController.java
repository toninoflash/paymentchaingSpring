/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.product.controller;

import com.paymentchain.product.common.IProductResponseMapper;
import com.paymentchain.product.common.IProductRquestMapper;
import com.paymentchain.product.dto.ProductRequestDto;
import com.paymentchain.product.dto.ProductResponseDto;
import com.paymentchain.product.entities.Product;
import com.paymentchain.product.exception.BusinessRuleException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.paymentchain.product.respository.ProductRepository;
import com.paymentchain.product.transaction.BusinessTrasaction;
import java.net.UnknownHostException;
import java.util.Optional;
import org.springframework.http.HttpStatus;

/**
 *
 * @author sotobotero
 */
@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BusinessTrasaction businessTrasaction;

    @Autowired
    IProductRquestMapper productRequestMapper;

    @Autowired
    IProductResponseMapper productResponseMapper;

    @GetMapping()
    public ResponseEntity<?> list() {
        List<Product> findAll = productRepository.findAll();
        List<ProductResponseDto> invoicesResp = productResponseMapper.ProductListToProductResponseList(findAll);

        if (invoicesResp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sin resultados");
        } else {
            return ResponseEntity.ok(invoicesResp);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") long id) {
        Optional<Product> product = productRepository.findById(id);
        Product response = productResponseMapper.toOptional(product);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") String id, @RequestBody ProductRequestDto input) throws BusinessRuleException {
        Product response = businessTrasaction.update(id, input);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ProductRequestDto input) throws BusinessRuleException, UnknownHostException {
        Product save = businessTrasaction.create(input);
        ProductResponseDto response = productResponseMapper.ProductToProductResponse(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {

        Optional<Product> findById = productRepository.findById(Long.valueOf(id));
        Product response = productResponseMapper.toOptional(findById);
        if (response != null) {
            productRepository.delete(findById.get());
            return ResponseEntity.ok().body(findById);

        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(findById);
        }
    }

}
