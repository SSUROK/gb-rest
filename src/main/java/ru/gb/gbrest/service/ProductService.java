package ru.gb.gbrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbrest.dao.ManufacturerDao;
import ru.gb.gbrest.dao.ProductDao;
import ru.gb.gbrest.dto.ProductDto;
import ru.gb.gbrest.dto.ProductManufacturerDto;
import ru.gb.gbrest.dto.mapper.ProductMapper;
import ru.gb.gbrest.entity.Product;
import ru.gb.gbrest.entity.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final ManufacturerDao manufacturerDao;

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto, manufacturerDao);
        if (product.getId() != null) {
            productDao.findById(product.getId()).ifPresent(
                    (p) -> product.setVersion(p.getVersion())
            );
        }
        return productMapper.toProductDto(productDao.save(product));
    }


    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        return productMapper.toProductDto(productDao.findById(id).orElse(null));
    }

    public List<ProductDto> findAll() {
        return productDao.findAll().stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("No such id in DB: " + e.getMessage());
        }
    }


    @Transactional(propagation = Propagation.NEVER, isolation = Isolation.DEFAULT)
    public long count() {
        System.out.println(productDao.count());
        return productDao.count();
    }

    public List<Product> findAll(int page, int size) {
        return productDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size));
    }

    public List<Product> findAllSortedById() {
        return productDao.findAllByStatus(Status.ACTIVE, Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Product> findAllSortedById(int page, int size) {
        return productDao.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    }

    public List<Product> findAllActive() {
        return productDao.findAllByStatus(Status.ACTIVE);
    }

    public List<ProductManufacturerDto> findAllInfo() {
        return productDao.findAll().stream().map(productMapper::toProductManufacturerDto).collect(Collectors.toList());
    }
}
