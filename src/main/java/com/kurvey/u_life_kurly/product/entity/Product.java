package com.kurvey.u_life_kurly.product.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliveryType;

    private String imageUrl;

    private String name;

    private int cost;

    private String description;

    @ApiModelProperty(example = "과일")
    @ManyToOne
    @JoinColumn
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}