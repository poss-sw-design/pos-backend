package com.pos.backend.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateRequest {

  @NotNull
  private Long merchantId;

  @NotBlank
  @Size(max = 255)
  private String name;

  @NotNull
  private Integer price;

  private Long productTypeId;
  private Long taxRateId;

  private String description;
  private String imageUrl;

  public Long getMerchantId() { return merchantId; }
  public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Integer getPrice() { return price; }
  public void setPrice(Integer price) { this.price = price; }

  public Long getProductTypeId() { return productTypeId; }
  public void setProductTypeId(Long productTypeId) { this.productTypeId = productTypeId; }

  public Long getTaxRateId() { return taxRateId; }
  public void setTaxRateId(Long taxRateId) { this.taxRateId = taxRateId; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
