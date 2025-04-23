/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.transaction;

import com.paymentchain.product.common.IProductResponseMapper;
import com.paymentchain.product.common.IProductRquestMapper;
import com.paymentchain.product.dto.ProductRequestDto;
import com.paymentchain.product.entities.Product;
import com.paymentchain.product.exception.BusinessRuleException;
import com.paymentchain.product.respository.ProductRepository;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author Pc
 */
@Service
public class BusinessTrasaction {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    IProductRquestMapper productRequestMapper;

    @Autowired
    IProductResponseMapper productResponseMapper;
    /*private final WebClient.Builder webClientBuilder;

    public CustomerRestController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }*/

    //webClient requires HttpClient library to work propertly       
    HttpClient client = HttpClient.create()
            //Connection Timeout: is a period within which a connection between a client and a server must be established
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            .option(EpollChannelOption.TCP_KEEPINTVL, 60)
            //Response Timeout: The maximun time we wait to receive a response after sending a request
            .responseTimeout(Duration.ofSeconds(1))
            // Read and Write Timeout: A read timeout occurs when no data was read within a certain 
            //period of time, while the write timeout when a write operation cannot finish at a specific time
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    public Product create(ProductRequestDto input) throws BusinessRuleException, UnknownHostException {
        Product save = new Product();
        if (input.getCode().isBlank() || input.getName().isBlank()) {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Producto sin code o name.", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        } else {
            Product product = productRequestMapper.ProductRequestToProduct(input);
            save = productRepository.save(product);
        }
        return save;
    }

    public Product update(String id, ProductRequestDto input) throws BusinessRuleException {
        Optional<Product> findById = productRepository.findById(Long.valueOf(id));
        Product response = productResponseMapper.toOptional(findById);
        if (response != null) {
            response.setId(Long.valueOf(id));
            response.setCode(input.getCode());
            response.setName(input.getName());
        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
        Product save = productRepository.save(response);

        return save;
    }
}
