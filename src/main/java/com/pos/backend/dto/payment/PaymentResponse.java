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
    return r;
  }

  public Long getPaymentId() { return paymentId; }
  public Long getOrderId() { return orderId; }
  public PaymentMethod getPaymentMethod() { return paymentMethod; }
  public PaymentStatus getStatus() { return status; }
  public Boolean getSplit() { return split; }
  public BigDecimal getTipAmount() { return tipAmount; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getProcessedAt() { return processedAt; }
}
