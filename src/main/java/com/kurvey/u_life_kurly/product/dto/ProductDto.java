package com.kurvey.u_life_kurly.product.dto;

import com.kurvey.u_life_kurly.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class ProductDto {
    @ApiModelProperty(value = "상품 Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "배송 유형", example = "샛별배송")
    @NotNull
    private String deliveryType;

    @ApiModelProperty(value = "상품 이미지 URL", example = "http://...")
    @NotNull
    private String imageUrl;

    @ApiModelProperty(value = "상품명", example = "아오리 사과 1.5kg(10입내)")
    @NotNull
    private String productName;

    @ApiModelProperty(value = "금액", example = "7900")
    @NotNull
    private int cost;

    @ApiModelProperty(value = "상품 한 줄 설명", example = "아오리 사과 1.5kg(10입내)")
    @NotNull
    private String description;

    public ProductDto(Product product){
        this.id = product.getId();
        this.deliveryType = product.getDeliveryType();
        this.productName = product.getName();
        this.imageUrl = product.getImageUrl();
        this.cost = product.getCost();
        this.description = product.getDescription();
    }

    public Product toEntity(){
        return Product.builder()
                .id(id)
                .deliveryType(deliveryType)
                .name(productName)
                .cost(cost)
                .description(description)
                .build();
    }
}
