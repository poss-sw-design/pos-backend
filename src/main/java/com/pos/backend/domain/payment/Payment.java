package com.pos.backend.domain.payment;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.order.Order;
import jakarta.persistence.*;

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
  private PaymentStatus status = PaymentStatus.PENDING;

  @Column(nullable = false)
  private Boolean split = false;

  @Column(nullable = false, precision = 10, scale = 2)
  private Double tipAmount = 0.0;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  private OffsetDateTime processedAt;

  protected Payment() {}

  public Long getPaymentId() { return paymentId; }

  public Order getOrder() { return order; }

  public PaymentStatus getStatus() { return status; }
}
