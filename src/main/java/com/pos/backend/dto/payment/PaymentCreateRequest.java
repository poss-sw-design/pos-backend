package com.pos.backend.dto.payment;

import com.pos.backend.domain.payment.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentCreateRequest {

  @NotNull
  private Long orderId;

  @NotNull
  private PaymentMethod paymentMethod;

  private Boolean split = false;

  private BigDecimal tipAmount = BigDecimal.ZERO;

  public Long getOrderId() { return orderId; }
  public void setOrderId(Long orderId) { this.orderId = orderId; }

  public PaymentMethod getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

  public Boolean getSplit() { return split; }
  public void setSplit(Boolean split) { this.split = split; }

  public BigDecimal getTipAmount() { return tipAmount; }
  public void setTipAmount(BigDecimal tipAmount) { this.tipAmount = tipAmount; }
}
