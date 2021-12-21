package ru.gb.gbrest.service.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.gbrest.dto.ManufacturerDto;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
public class ManufacturerDtoApiV2 {

    private static final String URL = "http://localhost:8456/manufacturer";

    private RestTemplate restTemplate;

    @Autowired
    public ManufacturerDtoApiV2(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ManufacturerDto create(ManufacturerDto manufacturerDto) {
        return restTemplate.postForObject(URI.create(URL), manufacturerDto, ManufacturerDto.class);
    }
}
