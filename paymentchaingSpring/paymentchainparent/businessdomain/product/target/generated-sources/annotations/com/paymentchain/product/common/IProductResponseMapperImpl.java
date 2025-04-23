package com.paymentchain.product.common;

import com.paymentchain.product.dto.ProductResponseDto;
import com.paymentchain.product.entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-23T11:04:49+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IProductResponseMapperImpl implements IProductResponseMapper {

    @Override
    public Product ProductResponseToProduct(ProductResponseDto source) {
        if ( source == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( source.getId() );
        product.setCode( source.getCode() );
        product.setName( source.getName() );

        return product;
    }

    @Override
    public List<Product> ProductResponseListToProductList(List<ProductResponseDto> source) {
        if ( source == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( source.size() );
        for ( ProductResponseDto productResponseDto : source ) {
            list.add( ProductResponseToProduct( productResponseDto ) );
        }

        return list;
    }

    @Override
    public ProductResponseDto ProductToProductResponse(Product source) {
        if ( source == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId( source.getId() );
        productResponseDto.setCode( source.getCode() );
        productResponseDto.setName( source.getName() );

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> ProductListToProductResponseList(List<Product> source) {
        if ( source == null ) {
            return null;
        }

        List<ProductResponseDto> list = new ArrayList<ProductResponseDto>( source.size() );
        for ( Product product : source ) {
            list.add( ProductToProductResponse( product ) );
        }

        return list;
    }

    @Override
    public Product toOptional(Optional<Product> source) {
        if ( source == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }
}
