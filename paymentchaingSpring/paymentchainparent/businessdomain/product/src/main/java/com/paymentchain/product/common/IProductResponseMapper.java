/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.paymentchain.product.common;

import com.paymentchain.product.dto.ProductResponseDto;
import com.paymentchain.product.entities.Product;
import java.util.List;
import java.util.Optional;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 *
 * @author Pc
 */
@Mapper(componentModel = "spring")
public interface IProductResponseMapper {
    Product ProductResponseToProduct(ProductResponseDto source);
    
    List<Product> ProductResponseListToProductList(List<ProductResponseDto> source);
    
    @InheritInverseConfiguration
    ProductResponseDto ProductToProductResponse(Product source);
    
    @InheritInverseConfiguration
    List<ProductResponseDto> ProductListToProductResponseList(List<Product> source);
    
    Product toOptional (Optional<Product> source);
}
