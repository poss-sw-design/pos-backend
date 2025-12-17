package com.pos.backend.dto.order;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public class OrderItemUpdateRequest {

  @Min(1)
  private Integer quantity;

  @Min(0)
  private BigDecimal unitPrice;

  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }

  public @Min(0) BigDecimal getUnitPrice() { return unitPrice; }
  public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
