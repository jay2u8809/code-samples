package com.jay2u8809.codesamples.corp.uzjp.bs.dto;

import com.jay2u8809.codesamples.corp.uzjp.bs.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    private String productName;

    private String productDesc;

    private String productCode;

    private BigDecimal productPrice;

    private BigDecimal productSalePrice;

    @Builder
    public ProductSaveRequestDto(String productName, String productDesc, String productCode, BigDecimal productPrice, BigDecimal productSalePrice) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productSalePrice = productSalePrice;
    }

    public Product toEntity() {
        return Product.builder()
                .productName(this.productName)
                .productDesc(this.productDesc)
                .productCode(this.productCode)
                .productPrice(this.productPrice)
                .productSalePrice(this.productSalePrice)
                .build();
    }
}
