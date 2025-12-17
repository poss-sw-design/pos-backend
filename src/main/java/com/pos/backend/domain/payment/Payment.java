package com.pos.backend.domain.payment;

import com.pos.backend.domain.order.Order;
import com.pos.backend.domain.Merchant;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id")
  private Merchant merchant;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  private Boolean split = false;
  private BigDecimal tipAmount = BigDecimal.ZERO;
  private OffsetDateTime createdAt = OffsetDateTime.now();
  private OffsetDateTime processedAt;

  public Long getPaymentId() { return paymentId; }
  public Order getOrder() { return order; }
  public void setOrder(Order order) { this.order = order; }
  public Merchant getMerchant() { return merchant; }
  public void setMerchant(Merchant merchant) { this.merchant = merchant; }
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
}
