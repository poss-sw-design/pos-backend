package com.pos.backend.service;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.product.Product;
import com.pos.backend.domain.product.ProductStatus;
import com.pos.backend.domain.product.ProductType;
import com.pos.backend.domain.product.TaxRate;
import com.pos.backend.dto.product.ProductCreateRequest;
import com.pos.backend.dto.product.ProductResponse;
import com.pos.backend.dto.product.ProductUpdateRequest;
import com.pos.backend.repository.MerchantRepository;
import com.pos.backend.repository.ProductRepository;
import com.pos.backend.repository.ProductTypeRepository;
import com.pos.backend.repository.TaxRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class ProductService {

  private final MerchantRepository merchantRepository;
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  private final TaxRateRepository taxRateRepository;

  public ProductService(MerchantRepository merchantRepository,
                        ProductRepository productRepository,
                        ProductTypeRepository productTypeRepository,
                        TaxRateRepository taxRateRepository) {

    this.merchantRepository = merchantRepository;
    this.productRepository = productRepository;
    this.productTypeRepository = productTypeRepository;
    this.taxRateRepository = taxRateRepository;
  }

  @Transactional
  public ProductResponse createProduct(ProductCreateRequest req) {

    Merchant merchant = merchantRepository.findById(req.getMerchantId())
      .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

    ProductType productType = null;
    if (req.getProductTypeId() != null) {
      productType = productTypeRepository.findById(req.getProductTypeId())
        .orElseThrow(() -> new IllegalArgumentException("ProductType not found"));
    }

    TaxRate taxRate = null;
    if (req.getTaxRateId() != null) {
      taxRate = taxRateRepository.findById(req.getTaxRateId())
        .orElseThrow(() -> new IllegalArgumentException("TaxRate not found"));
    }

    Product product = new Product(
      merchant,
      req.getName(),
      req.getPrice(),
      productType,
      taxRate,
      req.getDescription(),
      req.getImageUrl()
    );

    productRepository.save(product);
    return ProductResponse.from(product);
  }

  @Transactional(readOnly = true)
  public ProductResponse getProduct(Long productId) {

    Product p = productRepository.findById(productId)
      .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    if (p.getStatus() == ProductStatus.INACTIVE) {
      throw new IllegalStateException("Product is inactive");
    }

    return ProductResponse.from(p);
  }

  @Transactional
  public ProductResponse updateProduct(Long productId, ProductUpdateRequest req) {

    Product product = productRepository.findById(productId)
      .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    if (req.getName() != null) product.setName(req.getName());

    if (req.getPrice() != null) product.setPrice(req.getPrice());

    if (req.getProductTypeId() != null) {
      ProductType type = productTypeRepository.findById(req.getProductTypeId())
        .orElseThrow(() -> new IllegalArgumentException("ProductType not found"));
      product.setProductType(type);
    }

    if (req.getTaxRateId() != null) {
      TaxRate taxRate = taxRateRepository.findById(req.getTaxRateId())
        .orElseThrow(() -> new IllegalArgumentException("TaxRate not found"));
      product.setTaxRate(taxRate);
    }

    if (req.getDescription() != null) product.setDescription(req.getDescription());

    if (req.getImageUrl() != null) product.setImageUrl(req.getImageUrl());

    if (req.getStatus() != null) product.setStatus(req.getStatus());

    product.setUpdatedAt(OffsetDateTime.now());

    return ProductResponse.from(product);
  }

  @Transactional
  public void deleteProduct(Long productId) {

    Product product = productRepository.findById(productId)
      .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    product.setStatus(ProductStatus.INACTIVE);
    product.setUpdatedAt(OffsetDateTime.now());
  }
}
