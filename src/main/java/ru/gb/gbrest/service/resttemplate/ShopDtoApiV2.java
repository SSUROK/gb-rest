package ru.gb.gbrest.service.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.gbrest.dto.CartDto;
import ru.gb.gbrest.dto.ManufacturerDto;
import ru.gb.gbrest.dto.ProductDto;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class ShopDtoApiV2 {

    private static final String URL = "http://localhost:8455/cart";

    private RestTemplate restTemplate;

    @Autowired
    public ShopDtoApiV2(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CartDto getCart(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("cartId", id);
        return restTemplate.getForObject(URL, CartDto.class, params);
    }

    public void removeCart(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("cartId", id);
        restTemplate.delete(URL, params);
    }

    public CartDto addToCart(ProductDto productDto, Long id){
        Map<String, Long> params = new HashMap<>();
        params.put("cartId", id);
        return restTemplate.postForObject(URL, productDto, CartDto.class, params);
    }

    public void removeFromCart(ProductDto productDto, Long id){
        Map<String, Long> params = new HashMap<>();
        params.put("cartId", id);
        restTemplate.postForObject(URL + "/product", productDto, CartDto.class, params);
    }
}
