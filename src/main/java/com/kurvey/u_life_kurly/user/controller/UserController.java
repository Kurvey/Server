package com.kurvey.u_life_kurly.user.controller;

import com.kurvey.u_life_kurly.config.jwt.JwtTokenProvider;
import com.kurvey.u_life_kurly.config.jwt.PrincipalDetails;
import com.kurvey.u_life_kurly.product.entity.Product;
import com.kurvey.u_life_kurly.product.entity.Purchase;
import com.kurvey.u_life_kurly.product.service.ProductService;
import com.kurvey.u_life_kurly.product.service.PurchaseService;
import com.kurvey.u_life_kurly.response.Response;
import com.kurvey.u_life_kurly.user.dto.SignInDto;
import com.kurvey.u_life_kurly.user.dto.UserForm;
import com.kurvey.u_life_kurly.user.entity.User;
import com.kurvey.u_life_kurly.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kurvey.u_life_kurly.response.StatusCode.CREATE;
import static com.kurvey.u_life_kurly.response.StatusCode.SUCCESS;
import static org.springframework.http.HttpStatus.CREATED;

@Api(tags = "회원가입 / 로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final ProductService productService;

    @ApiOperation(value = "회원가입 API")
    @ResponseStatus(CREATED)
    @PostMapping("signup")
    public ResponseEntity<Response<?>> createUser(@RequestBody @Validated UserForm form) {
        Long id = userService.creatUser(form);
        return new Response<>(CREATE, Map.of("id", id)).toResponseEntity();
    }

    @ApiOperation(value = "로그인 API")
    @PostMapping("signin")
    public ResponseEntity<Response<?>> authoirize(@RequestBody @Validated SignInDto signInDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtTokenProvider.HEADER, userService.authorize(signInDto));
        return new Response<>(SUCCESS).toResponseEntity(headers);
    }


    //상품 추가
    @PostMapping("user/{id}/{purchaseid}")
    public ResponseEntity<Response<?>> myPurchaseProductAdd(@PathVariable("id") Long id, @PathVariable("productId") Long productId, int count) {
        User user = userService.findUser(id);
        Product product = productService.productView(productId);

        purchaseService.addPurchase(user, product, count);

        return new Response<>(CREATE, Map.of("product", product)).toResponseEntity();
    }


}

/*
    //주문내역
    @GetMapping("user/{id}/purchase")
    public ResponseEntity myPurchasePage(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        //로그인 user == 접속 user
        if (principalDetails.getUser().getId() == id){
            //user 주문 내역
            User user = userService.findUser(id);
            List<Purchase> purchaseList = (List<Purchase>) user.getPurchase();

            return new Response<>(CREATE, Map.of("purchaseList", purchaseList)).toResponseEntity();
        }else {
            return new Response<>(SUCCESS).toResponseEntity();
        }
    }*/







/*

    //장바구니 상품 추가
    @PostMapping("user/{id}/{purchaseid}")
    public String myPurchaseProductAdd(@PathVariable("id") Long id, @PathVariable("productId") Long productId, int count){
        User user = userService.findUser(id);
        Product product = productService.productView(productId);

        purchaseService.addPurchase(user, product, count);

        return new Response<>(data, SUCCESS).toResponseEntity();
    }
*/


//모델어트리뷰트는 안씀 rest 라서
/*
    //장바구니 조회
    @GetMapping("user/{id}/purchase")
    public void userPurchasePage(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //로그인 user == 접속 user
        if (principalDetails.getUser().getId() == id){

            //user의 장바구니 가져오기
            Purchase purchase = principalDetails.getUser().getPurchase();

            //장바구니 상품 가져오기
            List<PurchasedProduct> purchasedProductList = purchaseService.userPurchaseView(purchase);

            //장바구니 총 가격
            int totalcost = 0;
            for (PurchasedProduct purchasedProduct : purchasedProductList) {
                totalcost += (purchasedProduct.getCount() * purchasedProduct.getProduct().getCost());
            }

            model.addAttribute("purchaseProduct", purchasedProductList);
            model.addAttribute("totalCost", totalcost);
            model.addAttribute("totalCount", userProucts.getCount());
            model.addAttribute("user", userService.findUserId(id));

            return new Response<>(data, SUCCESS).toResponseEntity();
        }else {
            return new Response<>(SUCCESS).toResponseEntity();
        }
    }*/
