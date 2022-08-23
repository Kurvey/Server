package com.kurvey.u_life_kurly.product.dto;

import com.kurvey.u_life_kurly.product.entity.Category;
import com.kurvey.u_life_kurly.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    private Long id;

    @ApiModelProperty(name = "배송 유형", example = "샛별배송")
    @NotNull
    private String deliveryType;

    @NotNull
    private String imageUrl;    //이미지url받아야함

    @ApiModelProperty(name = "상품명", example = "아오리 사과 1.5kg(10입내)")
    @NotNull
    private String productName;

    @ApiModelProperty(name = "금액", example = "7900")
    @NotNull
    private int cost;

    @ApiModelProperty(name = "상품 한 줄 설명", example = "아오리 사과 1.5kg(10입내)")
    @NotNull
    private String description;



    public Product toEntity(){
        Product build = Product.builder()
                .id(id)
                .deliveryType(deliveryType)
                .name(productName)
                .cost(cost)
                .description(description)
                .build();
        return build;
    }

    @Builder
    public ProductDto(Long id, String  deliveryType, String productName, int cost, String description, Category category) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.productName = productName;
        this.cost = cost;
        this.description = description;
    }

}
