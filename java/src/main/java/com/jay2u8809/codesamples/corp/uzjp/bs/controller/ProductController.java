package com.jay2u8809.codesamples.corp.uzjp.bs.controller;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.bs.dto.ProductResponseDto;
import com.jay2u8809.codesamples.corp.uzjp.bs.dto.ProductSaveRequestDto;
import com.jay2u8809.codesamples.corp.uzjp.bs.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/corp/uzjp/bs/product")
public class ProductController extends CommonExtends {

    private static final String BASE_PATH = CORP_UZJP_HOME_PATH + "/bs/product/";

    private final ProductService productService;

    @GetMapping("/entry/")
    public String moveEntry(Model model) {
        model.addAttribute("entryUrl", BASE_PATH + "api/save/");
        return BASE_PATH + "productEntry";
    }

    @PostMapping("/api/save/")
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody ProductSaveRequestDto saveRequestDto) {

        Map<String, Object> result = new HashMap<>();
        result.put("productSn", productService.save(saveRequestDto));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/list/")
    public String moveList(Model model) {

        model.addAttribute("detailUrl", BASE_PATH + "detail/");
        model.addAttribute("products", productService.findAllProducts());
        return BASE_PATH + "productList";
    }

    @GetMapping("/api/get/all/")
    public List<ProductResponseDto> getProductList() {
        return productService.findAllProducts();
    }

    @GetMapping("/detail/{productSn}/")
    public String moveDetail(@PathVariable Long productSn, Model model) {

        model.addAttribute("product", productService.findById(productSn));
        return BASE_PATH + "productDetail";
    }

    @GetMapping("/api/get/{productSn}/")
    public ProductResponseDto getProduct(@PathVariable Long productSn) {
        return productService.findById(productSn);
    }


}
