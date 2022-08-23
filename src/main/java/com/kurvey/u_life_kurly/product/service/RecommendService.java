package com.kurvey.u_life_kurly.product.service;

import com.kurvey.u_life_kurly.error.CustomException;
import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.repository.ProductRepository;
import com.kurvey.u_life_kurly.response.StatusCode;
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
import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.*;

@RequiredArgsConstructor
@Service
public class RecommendService {
    private final ProductRepository productRepository;
    private final SimilarityRepository similarityRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> recommendProducts(User user, Long categoryId) {
        List<ProductDto> recommendedProducts = new ArrayList<>();
        List<Similarity> similarities = similarityRepository.findAllByUser(user, Sort.by(Sort.Direction.DESC, "value"));
        UserInfo userInfo = userInfoRepository.findByUser(user)
                .orElseThrow(() -> new CustomException(USER_INFO_DOES_NOT_EXIST));
        SimilarityCriteria similarityCriteria = new SimilarityCriteria(user.getBirthDay(), user.getGender(), userInfo);
        SimilaritySpecification similaritySpecification = new SimilaritySpecification(similarityCriteria);

//        for(Similarity similarity : similarities){
//            List<UserInfo> similarUsers = userInfoRepository.findAllBySelectionSet(similarity.getSelectionSet(), similaritySpecification);
//            System.out.println(similarUsers.size());
//        }
        return recommendedProducts;
    }
}
