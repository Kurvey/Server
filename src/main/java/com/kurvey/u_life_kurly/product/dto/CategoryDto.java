package com.kurvey.u_life_kurly.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryDto {
    private String category;

    private List<ProductDto> products;
}
