package com.jay2u8809.codesamples.corp.uzjp.bs.domain.product;

import com.jay2u8809.codesamples.corp.uzjp.bs.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    // PK Filed
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSn;

    @Column(length = 500, nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT")
    private String productDesc;

    private String productCode;

    private BigDecimal productPrice;

    private BigDecimal productSalePrice;

    private Boolean useFlag;

    @Builder
    public Product(Long productSn, String productName, String productDesc, String productCode, BigDecimal productPrice, BigDecimal productSalePrice, Boolean useFlag) {
        this.productSn = productSn;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productSalePrice = productSalePrice;
        this.useFlag = false;
    }

}
