package com.binar.batch7.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Must not empty")
    private String name;
    @NotNull(message = "Must not empty")
    private Double price;
    @NotNull(message = "Must not empty")
    private String merchantId;
}
