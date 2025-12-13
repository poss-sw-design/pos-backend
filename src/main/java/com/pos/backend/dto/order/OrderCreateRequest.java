package com.pos.backend.dto.order;

import jakarta.validation.constraints.NotNull;

public class OrderCreateRequest {

  @NotNull
  private Long merchantId;

  @NotNull
  private Long employeeId;

  @NotNull
  private String orderNumber;

  public Long getMerchantId() { return merchantId; }
  public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }

  public Long getEmployeeId() { return employeeId; }
  public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

  public String getOrderNumber() { return orderNumber; }
  public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
}
