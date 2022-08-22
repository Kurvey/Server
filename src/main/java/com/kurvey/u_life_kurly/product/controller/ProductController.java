package com.kurvey.u_life_kurly.product.controller;

import com.kurvey.u_life_kurly.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "조회")
@RestController
//@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "조회 API")
    @GetMapping("list")
    public String list() {
        return "kurly.html";
    }

/*    @ApiOperation(value = "검색 API")
    @GetMapping("search")
    public String search(@RequestBody(value = "keyword")String keyword, Model model) {
        List<>
    }*/
}
