package com.jay2u8809.codesamples.corp.uzjp.bs.service.product;

import com.jay2u8809.codesamples.corp.uzjp.bs.domain.product.Product;
import com.jay2u8809.codesamples.corp.uzjp.bs.domain.product.ProductRepository;
import com.jay2u8809.codesamples.corp.uzjp.bs.dto.ProductResponseDto;
import com.jay2u8809.codesamples.corp.uzjp.bs.dto.ProductSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long save(ProductSaveRequestDto saveRequestDto) {
        return productRepository.save(saveRequestDto.toEntity()).getProductSn();
    }

    public ProductResponseDto findById(Long productSn) {

        Product product = productRepository.findById(productSn)
                .orElseThrow(() -> new IllegalArgumentException("No Product. id=" + productSn));

        return new ProductResponseDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAllProducts() {

        return productRepository.findAll().stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());

    }

}
