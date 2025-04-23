package com.paymentchain.product.common;

import com.paymentchain.product.dto.ProductRequestDto;
import com.paymentchain.product.entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-23T11:04:49+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IProductRquestMapperImpl implements IProductRquestMapper {

    @Override
    public Product ProductRequestToProduct(ProductRequestDto source) {
        if ( source == null ) {
            return null;
        }

        Product product = new Product();

        product.setCode( source.getCode() );
        product.setName( source.getName() );

        return product;
    }

    @Override
    public List<Product> ProductRequestListToProductList(List<ProductRequestDto> source) {
        if ( source == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( source.size() );
        for ( ProductRequestDto productRequestDto : source ) {
            list.add( ProductRequestToProduct( productRequestDto ) );
        }

        return list;
    }

    @Override
    public ProductRequestDto ProductToProductRequest(Product source) {
        if ( source == null ) {
            return null;
        }

        ProductRequestDto productRequestDto = new ProductRequestDto();

        productRequestDto.setCode( source.getCode() );
        productRequestDto.setName( source.getName() );

        return productRequestDto;
    }

    @Override
    public List<ProductRequestDto> ProductListToProductRequestList(List<Product> source) {
        if ( source == null ) {
            return null;
        }

        List<ProductRequestDto> list = new ArrayList<ProductRequestDto>( source.size() );
        for ( Product product : source ) {
            list.add( ProductToProductRequest( product ) );
        }

        return list;
    }
}
