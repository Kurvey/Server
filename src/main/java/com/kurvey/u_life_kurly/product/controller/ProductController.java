package com.kurvey.u_life_kurly.product.controller;

import com.kurvey.u_life_kurly.config.jwt.PrincipalDetails;
import com.kurvey.u_life_kurly.product.dto.ProductsByCategoryDto;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.service.ProductService;
import com.kurvey.u_life_kurly.response.Response;
import com.kurvey.u_life_kurly.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.CREATE;
import static com.kurvey.u_life_kurly.response.StatusCode.SUCCESS;


@Api(tags = "검색")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "상품 검색 API")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<Response<?>> searchProducts(@PathVariable String keyword){
        List<ProductsByCategoryDto> products = productService.searchProducts(keyword);
        return new Response<>(SUCCESS, products).toResponseEntity();
    }

    //상품 추가
    @PostMapping
    public ResponseEntity<Response<?>> myPurchaseProductAdd(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                            Long productId) {
        User user = principalDetails.getUser();
        productService.buyProduct(user, productId);
        return new Response<>(CREATE).toResponseEntity();
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<?>> findProductById(@PathVariable Long id){
        Product product = productService.findProductById(id);
        return new Response<>(SUCCESS, product).toResponseEntity();
    }
}
