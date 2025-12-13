package com.pos.backend.dto.order;

import com.pos.backend.domain.order.OrderStatus;

public class OrderUpdateRequest {

  private OrderStatus status;
  private String specialRequests;
  private Long discountId;

  public OrderStatus getStatus() { return status; }
  public void setStatus(OrderStatus status) { this.status = status; }

  public String getSpecialRequests() { return specialRequests; }
  public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }

  public Long getDiscountId() { return discountId; }
  public void setDiscountId(Long discountId) { this.discountId = discountId; }
}
