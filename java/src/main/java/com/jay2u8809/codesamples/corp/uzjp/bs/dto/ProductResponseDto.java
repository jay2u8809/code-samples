package com.jay2u8809.codesamples.corp.uzjp.bs.dto;

import com.jay2u8809.codesamples.corp.uzjp.bs.domain.product.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponseDto {

    private Long productSn;

    private String productName;

    private String productDesc;

    private String productCode;

    private BigDecimal productPrice;

    private BigDecimal productSalePrice;

    private Boolean useFlag;

    public ProductResponseDto(Product entity) {
        this.productSn = entity.getProductSn();
        this.productName = entity.getProductName();
        this.productDesc = entity.getProductDesc();
        this.productCode = entity.getProductCode();
        this.productPrice = entity.getProductPrice();
        this.productSalePrice = entity.getProductSalePrice();
        this.useFlag = entity.getUseFlag();
    }
}
