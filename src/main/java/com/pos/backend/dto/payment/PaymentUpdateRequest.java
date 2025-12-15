package com.pos.backend.dto.payment;

import com.pos.backend.domain.payment.PaymentMethod;

import java.math.BigDecimal;

public class PaymentUpdateRequest {

  private PaymentMethod paymentMethod;
  private Boolean split;
  private BigDecimal tipAmount;

  public PaymentMethod getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

  public Boolean getSplit() { return split; }
  public void setSplit(Boolean split) { this.split = split; }

  public BigDecimal getTipAmount() { return tipAmount; }
  public void setTipAmount(BigDecimal tipAmount) { this.tipAmount = tipAmount; }

}
