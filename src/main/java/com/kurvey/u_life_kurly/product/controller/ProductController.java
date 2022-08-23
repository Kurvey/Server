package com.kurvey.u_life_kurly.product.controller;

import com.kurvey.u_life_kurly.product.dto.CategoryDto;
import com.kurvey.u_life_kurly.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@Api(tags = "검색")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "상품 검색")
    @PostMapping("searchresult/products")
    public ResponseEntity searchproducts(@RequestBody Map<String, String> body){
        String keyword = body.get("keyword");
        List<CategoryDto> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

}
