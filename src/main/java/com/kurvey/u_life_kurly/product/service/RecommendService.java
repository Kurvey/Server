package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.entity.Purchase;
import com.kurvey.u_life_kurly.product.entity.PurchasedProduct;
import com.kurvey.u_life_kurly.product.repository.PurchaseRepository;
import com.kurvey.u_life_kurly.product.repository.PurchasedProductRepository;
import com.kurvey.u_life_kurly.user.dto.SimilarityCriteria;
import com.kurvey.u_life_kurly.user.entity.Similarity;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.entity.UserInfo;
import com.kurvey.u_life_kurly.user.repository.SimilarityRepository;
import com.kurvey.u_life_kurly.user.repository.SimilaritySpecification;
import com.kurvey.u_life_kurly.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kurvey.u_life_kurly.response.StatusCode.USER_INFO_DOES_NOT_EXIST;

@RequiredArgsConstructor
@Service
public class RecommendService {
    private static final int RECOMMEND_COUNT = 7;
    private final SimilarityRepository similarityRepository;
    private final UserInfoRepository userInfoRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchasedProductRepository purchasedProductRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> recommendProducts(User user, Long categoryId) {
        List<ProductDto> recommendedProducts = new ArrayList<>();
        List<Similarity> similarities = similarityRepository.findAllByUser(user, Sort.by(Sort.Direction.DESC, "value"));
        UserInfo userInfo = userInfoRepository.findByUser(user)
                .orElseThrow(() -> new CustomException(USER_INFO_DOES_NOT_EXIST));
        SimilarityCriteria similarityCriteria = new SimilarityCriteria(user.getBirthDay(), user.getGender(), userInfo);

        for(Similarity similarity : similarities){
            SimilaritySpecification similaritySpecification = new SimilaritySpecification(similarityCriteria, similarity.getSelectionSet(), user);
            List<UserInfo> similarUsers = userInfoRepository.findAll(similaritySpecification);

            List<Purchase> purchases = similarUsers.stream().map(ui -> purchaseRepository.findAllByUser(ui.getUser()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            List<ProductDto> products = purchasedProductRepository.findAllByPurchaseIsInAndProductCategoryId(purchases, categoryId).stream()
                    .collect(Collectors.groupingBy(PurchasedProduct::getProduct,
                            Collectors.summingInt(PurchasedProduct::getQuantity)))
                    .entrySet().stream()
                    .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                    .filter(e -> e.getValue() > 1)
                    .map(Map.Entry::getKey)
                    .map(ProductDto::new)
                    .collect(Collectors.toList());

            recommendedProducts.addAll(products);
            if(recommendedProducts.size() >= RECOMMEND_COUNT) break;
        }
        return recommendedProducts.subList(0, Math.min(recommendedProducts.size(), RECOMMEND_COUNT));
    }
}
