package com.pos.backend.dto.product;

import com.pos.backend.domain.product.ProductStatus;
import jakarta.validation.constraints.Size;

public class ProductUpdateRequest {

  @Size(max = 255)
  private String name;

  private Integer price;

  private Long productTypeId;

  private Long taxRateId;

  @Size(max = 1000)
  private String description;

  private String imageUrl;

  private ProductStatus status;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Long getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(Long productTypeId) {
    this.productTypeId = productTypeId;
  }

  public Long getTaxRateId() {
    return taxRateId;
  }

  public void setTaxRateId(Long taxRateId) {
    this.taxRateId = taxRateId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public ProductStatus getStatus() {
    return status;
  }

  public void setStatus(ProductStatus status) {
    this.status = status;
  }
}
