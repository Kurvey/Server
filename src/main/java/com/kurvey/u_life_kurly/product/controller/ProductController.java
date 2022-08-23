package com.kurvey.u_life_kurly.product.controller;

import com.kurvey.u_life_kurly.product.dto.ProductsByCategoryDto;
import com.kurvey.u_life_kurly.product.service.ProductService;
import com.kurvey.u_life_kurly.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kurvey.u_life_kurly.response.StatusCode.SUCCESS;


@Api(tags = "검색")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "상품 검색 API")
    @GetMapping("{keyword}")
    public ResponseEntity<Response<?>> searchProducts(@PathVariable String keyword){
        List<ProductsByCategoryDto> products = productService.searchProducts(keyword);
        return new Response<>(SUCCESS, products).toResponseEntity();
    }

}
