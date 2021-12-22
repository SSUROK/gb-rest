package ru.gb.gbrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    @JsonProperty(value = "cartId")
    private Long id;
    @JsonProperty(value = "productList")
    private Set<ProductDto> productList;
}
