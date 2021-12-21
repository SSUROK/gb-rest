package ru.gb.gbrest.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.gbrest.dto.ManufacturerDto;
import ru.gb.gbrest.entity.Manufacturer;

@Mapper
public interface ManufacturerMapper {
    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);

    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);
}
