package com.pos.backend.dto.product;

import com.pos.backend.domain.product.Product;
import com.pos.backend.domain.product.ProductStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ProductResponse {

  private Long productId;
  private Long merchantId;
  private String name;
  private Integer price;

  private Long productTypeId;
  private String productTypeName;

  private Long taxRateId;
  private BigDecimal taxRateValue;

  private String description;
  private String imageUrl;
  private ProductStatus status;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public static ProductResponse from(Product p) {
    ProductResponse r = new ProductResponse();

    r.productId = p.getProductId();
    r.merchantId = p.getMerchant().getMerchantId();
    r.name = p.getName();
    r.price = p.getPrice();

    if (p.getProductType() != null) {
      r.productTypeId = p.getProductType().getProductTypeId();
      r.productTypeName = p.getProductType().getProductTypeName();
    }

    if (p.getTaxRate() != null) {
      r.taxRateId = p.getTaxRate().getTaxRateId();
      r.taxRateValue = p.getTaxRate().getRate();
    }

    r.description = p.getDescription();
    r.imageUrl = p.getImageUrl();
    r.status = p.getStatus();

    r.createdAt = p.getCreatedAt();
    r.updatedAt = p.getUpdatedAt();

    return r;
  }

  public Long getProductId() { return productId; }
  public Long getMerchantId() { return merchantId; }
  public String getName() { return name; }
  public Integer getPrice() { return price; }

  public Long getProductTypeId() { return productTypeId; }
  public String getProductTypeName() { return productTypeName; }

  public Long getTaxRateId() { return taxRateId; }
  public BigDecimal getTaxRateValue() { return taxRateValue; }

  public String getDescription() { return description; }
  public String getImageUrl() { return imageUrl; }
  public ProductStatus getStatus() { return status; }

  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
}
