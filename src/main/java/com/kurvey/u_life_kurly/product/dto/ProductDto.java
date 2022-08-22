package com.kurvey.u_life_kurly.product.dto;

import com.kurvey.u_life_kurly.product.entity.Category;
import com.kurvey.u_life_kurly.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @ApiModelProperty(name = "상품명", example = "아오리 사과 1.5kg(10입내)")
    @NotNull
    private String name;

    @ApiModelProperty(name = "상품 한 줄 설명", example = "아오리 사과 1.5kg(10입내)")
    @NotNull
    private String description;

    @ApiModelProperty(name = "금액", example = "7900")
    @NotNull
    private int cost;

    @ApiModelProperty(name = "배송 유형", example = "샛별배송")
    @NotNull
    private String deliveryType;

    @ApiModelProperty(name = "카테고리명", example = "과일")
    @NotNull
    private Category category;

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .description(description)
                .cost(cost)
                .deliveryType(description)
                .category(category)
                .build();
    }

}
