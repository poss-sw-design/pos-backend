package com.pos.backend.dto.order;

import jakarta.validation.constraints.Min;

public class OrderItemUpdateRequest {

  @Min(1)
  private Integer quantity;

  @Min(0)
  private Integer unitPrice;

  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }

  public Integer getUnitPrice() { return unitPrice; }
  public void setUnitPrice(Integer unitPrice) { this.unitPrice = unitPrice; }
}
