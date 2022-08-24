package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.entity.Purchase;
import com.kurvey.u_life_kurly.product.entity.PurchasedProduct;
import com.kurvey.u_life_kurly.product.repository.ProductRepository;
import com.kurvey.u_life_kurly.product.repository.PurchaseProductRepository;
import com.kurvey.u_life_kurly.product.repository.PurchaseRepository;
import com.kurvey.u_life_kurly.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final ProductRepository productRepository;


    //장바구니에 상품 추가
    @Transactional
    public void addPurchase(User user, Product product, int count){
        //user id로 헤당 user의 장바구니 찾기
        Purchase purchase = purchaseRepository.findByUserId(user.getId());


        //상품 생성
        PurchasedProduct purchasedProduct = purchaseProductRepository.findByPurchaseAndId(purchase.getId(), product.getId());

    }


    //주문
    public void createPurchase(User user){
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchaseRepository.save(purchase);
    }


}



/*
    //장바구니에 상품 추가
    @Transactional
    public void addPurchase(User user, Product product, int count){
        //user id로 헤당 user의 장바구니 찾기
        Purchase purchase = purchaseRepository.findByUserId(user.getId());

*//*        //장바구니 비어있으면 생성
        if(purchase == null){
            purchase = Purchase.createPurchase(user);
            purchaseRepository.save(purchase);
        }*//*

        //상품 생성
        PurchasedProduct purchasedProduct = purchaseProductRepository.findByPurchaseAndPurchasedProduct(purchase.getId(), product.getId());

*//*        //상품이 장바구니에 존재하지 않으면 장바구니 상품 생성 후 추가
        if (purchasedProduct == null) {
            purchasedProduct = PurchasedProduct.createPurchasedProduct(purchase, product);
            purchaseProductRepository.save(purchasedProduct);
        }else { //상품이 장바구니에 이미 존재한다면 수량만 증가
            PurchasedProduct update = purchasedProduct;
            update.setPurchase(purchasedProduct.getPurchase());
            update.setProduct(purchasedProduct.getProduct());
            update.setCount(update.getCount());
            purchaseProductRepository.save(update);
        }*//*

        */

/*    //장바구니에 상품 추가
    @Transactional
    public void addPurchase(User user, Product product, int count){
        //user id로 헤당 user의 장바구니 찾기
        Purchase purchase = purchaseRepository.findByUser(user.getId());

        //장바구니 비어있으면 생성
        if(purchase == null){
            purchase = Purchase.createPurchase(user);
            purchaseRepository.save(purchase);
        }

        //Optional<Product> product = productRepository.findAllByNameContaining(newProduct.getId());
        PurchasedProduct purchasedProduct = purchaseProductRepository.findByPurchaseAndPurchasedProduct(purchase.getId(), product.getId());

        //상품이 장바구니에 존재하지 않으면 장바구니 상품 생성 후 추가
        if (purchasedProduct == null) {
            purchasedProduct = PurchasedProduct.createPurchasedProduct(purchase, product, count);
            purchaseProductRepository.save(purchasedProduct);
        }else { //상품이 장바구니에 이미 존재한다면 수량만 증가
            PurchasedProduct update = purchasedProduct;
            update.setPurchase(purchasedProduct.getPurchase());
            update.setProduct(purchasedProduct.getProduct());
            update.addCount(count);
            update.setCount(update.getCount());
            purchaseProductRepository.save(update);
        }

    }*/


/*
    //장바구니 조회하기
    public List<PurchasedProduct> userPurchaseView(Purchase purchase){
        List<PurchasedProduct> purchasedProducts = purchaseProductRepository.findAll();
        List<PurchasedProduct> userProducts = new ArrayList<>();

        for(PurchasedProduct purchasedProduct : purchasedProducts){
            if(purchasedProduct.getPurchase().getId() == purchase.getId()){
                userProducts.add(purchasedProduct);
            }
        }
        return userProducts;
    }*/

/*

    //user에게 장바구니 생성
    public void createPurchase(User user){
        Purchase purchase = Purchase.createPurchase(user);
        PurchaseRepository.save(purchase);
    }
*/


/*    //장바구니 상품 삭제
    public void purchaserProductDelete(int id){
        purchaseProductRepository.deleteById(id);
    }*/


