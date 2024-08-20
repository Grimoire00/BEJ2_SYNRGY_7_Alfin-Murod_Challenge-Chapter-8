package com.binar.batch7.service;

import com.binar.batch7.dto.MerchantRequest;
import com.binar.batch7.dto.MerchantResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MerchantService {

    MerchantResponse create(MerchantRequest request);

    List<MerchantResponse> findAll(Boolean isOpen, Pageable pageable, String name, String location);

    MerchantResponse update(UUID id, MerchantRequest request);

    MerchantResponse delete(UUID id);

    MerchantResponse findById(UUID id);

}
