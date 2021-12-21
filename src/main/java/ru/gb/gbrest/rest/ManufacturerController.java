package ru.gb.gbrest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.gbrest.dto.ManufacturerDto;
import ru.gb.gbrest.service.feign.ManufacturerDtoApi;
import ru.gb.gbrest.service.resttemplate.ManufacturerDtoApiV2;

@RestController
@RequestMapping("/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerDtoApiV2 manufacturerDtoApi;

    @PostMapping
    public ManufacturerDto create(@RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerDtoApi.create(manufacturerDto);
    }
}
