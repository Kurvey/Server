package com.kurvey.u_life_kurly.product.entity;

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

    private String name; //상품명

    private String description; //한 줄 설명

    @ManyToOne
    private Category category;

    private int cost; //금액

    private String deliveryType;  //배송 유형

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
