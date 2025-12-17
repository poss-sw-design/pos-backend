package com.pos.backend.dto.payment;

import com.pos.backend.domain.payment.Payment;
import com.pos.backend.domain.payment.PaymentMethod;
import com.pos.backend.domain.payment.PaymentStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PaymentResponse {

  private Long paymentId;
  private Long orderId;
  private PaymentMethod paymentMethod;
  private PaymentStatus status;
  private Boolean split;
  private BigDecimal tipAmount;
  private OffsetDateTime createdAt;
  private OffsetDateTime processedAt;
  private BigDecimal taxAmount;

  public static PaymentResponse from(Payment payment) {
    PaymentResponse r = new PaymentResponse();
    r.paymentId = payment.getPaymentId();
    r.orderId = payment.getOrder().getOrderId();
    r.paymentMethod = payment.getPaymentMethod();
    r.status = payment.getStatus();
    r.split = payment.getSplit();
    r.tipAmount = payment.getTipAmount();
    r.createdAt = payment.getCreatedAt();
    r.processedAt = payment.getProcessedAt();
    if (payment.getOrder() != null) {
      r.taxAmount = payment.getOrder().getTaxAmount();
    }
    return r;
  }

  public Long getPaymentId() { return paymentId; }
  public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

  public Long getOrderId() { return orderId; }
  public void setOrderId(Long orderId) { this.orderId = orderId; }

  public PaymentMethod getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

  public PaymentStatus getStatus() { return status; }
  public void setStatus(PaymentStatus status) { this.status = status; }

  public Boolean getSplit() { return split; }
  public void setSplit(Boolean split) { this.split = split; }

  public BigDecimal getTipAmount() { return tipAmount; }
  public void setTipAmount(BigDecimal tipAmount) { this.tipAmount = tipAmount; }

  public OffsetDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

  public OffsetDateTime getProcessedAt() { return processedAt; }
  public void setProcessedAt(OffsetDateTime processedAt) { this.processedAt = processedAt; }

  public BigDecimal getTaxAmount() { return taxAmount; }
  public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
}
