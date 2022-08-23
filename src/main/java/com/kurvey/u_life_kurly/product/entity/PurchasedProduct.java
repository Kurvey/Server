package com.kurvey.u_life_kurly.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchasedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count = 0;

    private int cost;   //금액

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Purchase purchase;  //주문연결

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;     //상품연결

/*    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime paidAt;*/

    public static PurchasedProduct createPurchasedProduct(Product product, int count) {
        PurchasedProduct purchasedProduct = new PurchasedProduct();
        purchasedProduct.setProduct(product);
        purchasedProduct.setCount(count);
        purchasedProduct.setCost(product.getCost());

        return purchasedProduct;
    }



}


/*    //이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(int count) {
        this.count += count;
    }*/

//구매할때 사용자 정보 필요함.  @authenticationprincipal 어노테이션, 토큰으로 유저를 알아오는것임

//파람을 @authenticationprincipal PrincipalDetails principalDetails ;principalDetails.getUser()