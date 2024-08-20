package com.binar.batch7.service;

import com.binar.batch7.dto.OrderDetailRequest;
import com.binar.batch7.dto.OrderDetailResponse;
import com.binar.batch7.dto.OrderRequest;
import com.binar.batch7.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse create(OrderRequest request, Principal principal);

    List<OrderResponse> findAll();

    OrderResponse update(UUID id, OrderRequest request);

    OrderResponse delete(UUID id);

    Page<OrderDetailResponse> findAllDetails(Pageable pageable);

    OrderDetailResponse createDetail(OrderDetailRequest request);
}
