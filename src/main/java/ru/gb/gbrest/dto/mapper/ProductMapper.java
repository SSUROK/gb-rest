package ru.gb.gbrest.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.gbrest.dao.ManufacturerDao;
import ru.gb.gbrest.dto.ProductDto;
import ru.gb.gbrest.dto.ProductManufacturerDto;
import ru.gb.gbrest.entity.Manufacturer;
import ru.gb.gbrest.entity.Product;

import java.util.NoSuchElementException;

@Mapper(uses = ManufacturerMapper.class)
public interface ProductMapper {
    Product toProduct(ProductDto productDto, @Context ManufacturerDao manufacturerDao);

    ProductDto toProductDto(Product product);

    @Mapping(source = "manufacturer", target = "manufacturerDto")
    ProductManufacturerDto toProductManufacturerDto(Product product);

    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao) {
        return manufacturerDao.findByName(manufacturer).orElseThrow(NoSuchElementException::new);
    }

    default String getManufacturer(Manufacturer manufacturer) {
        return manufacturer.getName();
    }
}
