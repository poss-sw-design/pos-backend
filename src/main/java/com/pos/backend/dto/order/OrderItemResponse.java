package com.pos.backend.dto.order;

import com.pos.backend.domain.order.OrderItem;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrderItemResponse {

  private Long orderItemId;
  private Long productId;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal taxRate;
  private BigDecimal taxAmount;
  private OffsetDateTime createdAt;

  public static OrderItemResponse from(OrderItem item) {
    OrderItemResponse r = new OrderItemResponse();
    r.orderItemId = item.getOrderItemId();
    r.productId = item.getProduct().getProductId();
    r.quantity = item.getQuantity();
    r.unitPrice = item.getUnitPrice();
    r.taxRate = item.getTaxRate() != null ? item.getTaxRate() : BigDecimal.ZERO;
    r.taxAmount = item.getTaxAmount() != null ? item.getTaxAmount() : BigDecimal.ZERO;
    r.createdAt = item.getCreatedAt();
    return r;
  }

  public Long getOrderItemId() { return orderItemId; }
  public Long getProductId() { return productId; }
  public Integer getQuantity() { return quantity; }
  public BigDecimal getUnitPrice() { return unitPrice; }
  public BigDecimal getTaxRate() { return taxRate; }
  public BigDecimal getTaxAmount() { return taxAmount; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
}
