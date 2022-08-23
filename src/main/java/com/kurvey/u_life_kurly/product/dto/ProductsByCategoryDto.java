package com.kurvey.u_life_kurly.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ProductsByCategoryDto {
    private CategoryDto category;

    private List<ProductDto> products;

    public ProductsByCategoryDto(Long categoryId, String categoryName, List<ProductDto> products) {
        this.category = new CategoryDto(categoryId, categoryName);
        this.products = products;
    }

    @Data
    @AllArgsConstructor
    static class CategoryDto {
        @ApiModelProperty(value = "카테고리 Id", example = "1")
        private Long id;

        @ApiModelProperty(value = "카테고리 명", example = "과일")
        private String name;
    }
}

