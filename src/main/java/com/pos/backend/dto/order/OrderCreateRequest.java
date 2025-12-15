package com.pos.backend.dto.order;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderCreateRequest {

  @NotNull
  private Long merchantId;

  @NotNull
  private Long employeeId;

  @NotNull
  private String orderNumber;

  private List<OrderItemCreateRequest> items;

  public Long getMerchantId() { return merchantId; }
  public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }

  public Long getEmployeeId() { return employeeId; }
  public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

  public String getOrderNumber() { return orderNumber; }
  public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

  public List<OrderItemCreateRequest> getItems() { return items; }
  public void setItems(List<OrderItemCreateRequest> items) { this.items = items; }
}
