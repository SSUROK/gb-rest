package ru.gb.gbrest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrest.dto.CartDto;
import ru.gb.gbrest.dto.ManufacturerDto;
import ru.gb.gbrest.dto.ProductDto;
import ru.gb.gbrest.service.resttemplate.ShopDtoApiV2;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ShopDtoApiV2 shopDtoApi;
    private Long cartId;

    @PostMapping
    public CartDto add(@RequestBody ProductDto productDto) {
        CartDto cartDto = shopDtoApi.addToCart(productDto, cartId);
        cartId = cartDto.getId();
        return cartDto;
    }

    @DeleteMapping
    public void removeFromCart(@RequestBody ProductDto productDto) {
        shopDtoApi.removeFromCart(productDto, cartId);
    }

    @DeleteMapping
    public void removeCart() {
        shopDtoApi.removeCart(cartId);
    }

    @GetMapping
    public CartDto getCart() {
        return shopDtoApi.getCart(cartId);
    }

}
