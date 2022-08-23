package com.kurvey.u_life_kurly.product.controller;

import com.kurvey.u_life_kurly.config.jwt.PrincipalDetails;
import com.kurvey.u_life_kurly.product.dto.ProductDto;
import com.kurvey.u_life_kurly.product.service.RecommendService;
import com.kurvey.u_life_kurly.response.Response;
import com.kurvey.u_life_kurly.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.SUCCESS;

@Api(tags = "상품 추천")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/recommend")
public class RecommendController {
    private final RecommendService recommendService;

    @ApiOperation(value = "상품 추천 API")
    @GetMapping("{categoryId}")
    public ResponseEntity<Response<?>> recommendProducts(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                         @PathVariable Long categoryId){
        User user = principalDetails.getUser();
        List<ProductDto> products = recommendService.recommendProducts(user, categoryId);
        return new Response<>(SUCCESS, products).toResponseEntity();
    }
}
