package com.pos.backend.domain.payment;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.order.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false, unique = true)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private PaymentStatus status;

  @Column(nullable = false)
  private Boolean split = false;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal tipAmount = BigDecimal.ZERO;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  private OffsetDateTime processedAt;

  protected Payment() {}

  public Long getPaymentId() { return paymentId; }
  public Order getOrder() { return order; }
  public PaymentMethod getPaymentMethod() { return paymentMethod; }
  public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
  public PaymentStatus getStatus() { return status; }
  public void setStatus(PaymentStatus status) { this.status = status; }
  public Boolean getSplit() { return split; }
  public void setSplit(Boolean split) { this.split = split; }
  public BigDecimal getTipAmount() { return tipAmount; }
  public void setTipAmount(BigDecimal tipAmount) { this.tipAmount = tipAmount; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getProcessedAt() { return processedAt; }
  public void setProcessedAt(OffsetDateTime processedAt) { this.processedAt = processedAt; }
}
