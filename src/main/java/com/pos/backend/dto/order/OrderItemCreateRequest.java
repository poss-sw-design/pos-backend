package com.pos.backend.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class OrderItemCreateRequest {

  @NotNull
  private Long productId;

  @Min(1)
  private Integer quantity;

  @Min(0)
  private Integer unitPrice;

  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }

  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }

  public Integer getUnitPrice() { return unitPrice; }
  public void setUnitPrice(Integer unitPrice) { this.unitPrice = unitPrice; }
}
