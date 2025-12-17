package com.pos.backend.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public class OrderItemCreateRequest {

  @NotNull
  private Long productId;

  @Min(1)
  private Integer quantity;

  @Min(0)
  private BigDecimal unitPrice;

  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }

  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }

  public @Min(0) BigDecimal getUnitPrice() { return unitPrice; }
  public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
