package com.kurvey.u_life_kurly.product.entity;

import com.kurvey.u_life_kurly.user.entity.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //purchase id

    private int cost; //총금액

    @OneToOne(fetch = FetchType.EAGER)
    private User user;  //구매자

    //@OneToMany(mappedBy = "purchase")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PurchasedProduct> purchasedProducts = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime paidAt;   //구매날짜

    @PrePersist
    public void paidAt(){
        this.paidAt = LocalDateTime.now();
    }

    public void addPurchasedProduct(PurchasedProduct purchasedProduct) {
        purchasedProducts.add(purchasedProduct);
        purchasedProduct.setPurchase(this);
    }


    public static Purchase createPurchase(User user, List<PurchasedProduct> purchasedProductList) {
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        for (PurchasedProduct purchasedProduct : purchasedProductList){
            purchase.addPurchasedProduct(purchasedProduct);
        }
        purchase.setPaidAt(LocalDateTime.now());
        return purchase;
    }

    public int getTotalCost(){
        int totalCost = 0;

        for (PurchasedProduct purchasedProduct : purchasedProducts){
            totalCost += (purchasedProduct.getCost() * purchasedProduct.getCount());
        }
        return totalCost;
    }
}
