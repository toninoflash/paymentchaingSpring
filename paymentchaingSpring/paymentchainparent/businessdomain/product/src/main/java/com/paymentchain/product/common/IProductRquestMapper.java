/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.product.common;

import com.paymentchain.product.dto.ProductRequestDto;
import com.paymentchain.product.entities.Product;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface IProductRquestMapper {
    Product ProductRequestToProduct(ProductRequestDto source);
    
    List<Product> ProductRequestListToProductList(List<ProductRequestDto> source);
    
    @InheritInverseConfiguration
    ProductRequestDto ProductToProductRequest(Product source);
    
    @InheritInverseConfiguration
    List<ProductRequestDto> ProductListToProductRequestList(List<Product> source);
}
