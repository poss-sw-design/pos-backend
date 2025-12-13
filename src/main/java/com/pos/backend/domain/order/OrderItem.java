package com.pos.backend.domain.order;

import com.pos.backend.domain.product.Product;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "order_item",
  uniqueConstraints = {@UniqueConstraint(name = "uk_order_product", columnNames = {"order_id", "product_id"})})
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderItemId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private Integer quantity = 1;

  @Column(nullable = false)
  private Integer unitPrice;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  protected OrderItem() {}

  public OrderItem(Product product, Integer quantity, Integer unitPrice) {
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  public Long getOrderItemId() { return orderItemId; }
  public Order getOrder() { return order; }
  public void setOrder(Order order) { this.order = order; }
  public Product getProduct() { return product; }
  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }
  public Integer getUnitPrice() { return unitPrice; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
}
