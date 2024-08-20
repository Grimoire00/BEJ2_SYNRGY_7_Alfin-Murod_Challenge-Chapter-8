package com.binar.batch7.service;

import com.binar.batch7.dto.ProductRequest;
import com.binar.batch7.dto.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    List<ProductResponse> findAll(Pageable pageable, String name, Double price);

    ProductResponse update(UUID id, ProductRequest request);

    ProductResponse delete(UUID id);

    ProductResponse findById(UUID id);

    List<ProductResponse> findByPromo(Double priceThreshold);
}
