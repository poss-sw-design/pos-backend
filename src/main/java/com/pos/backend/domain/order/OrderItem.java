package com.pos.backend.domain.order;

import com.pos.backend.domain.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Entity
@Table(
  name = "order_item",
  uniqueConstraints = {@UniqueConstraint(name = "uk_order_product", columnNames = {"order_id", "product_id"})}
)
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
  private BigDecimal unitPrice;

  @Column(nullable = false)
  private BigDecimal taxRate;

  @Column(nullable = false)
  private BigDecimal taxAmount;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  protected OrderItem() {}

  public OrderItem(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity != null ? quantity : 1;
    this.unitPrice = BigDecimal.valueOf(product.getPrice());
    this.taxRate = product.getTaxRate() != null ? product.getTaxRate().getRate() : BigDecimal.ZERO;
    calculateTax();
  }

  @PrePersist
  @PreUpdate
  public void calculateTax() {
    if (taxRate == null) taxRate = BigDecimal.ZERO;
    if (unitPrice == null) unitPrice = BigDecimal.ZERO;
    if (quantity == null) quantity = 0;

    this.taxAmount = unitPrice
      .multiply(BigDecimal.valueOf(quantity))
      .multiply(taxRate)
      .setScale(2, RoundingMode.HALF_UP);
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity != null ? quantity : 1;
    calculateTax();
  }

  public void setTaxAmount(BigDecimal itemTax) {
    this.taxAmount = itemTax != null ? itemTax.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
  }

  public Long getOrderItemId() { return orderItemId; }
  public Order getOrder() { return order; }
  public void setOrder(Order order) { this.order = order; }
  public Product getProduct() { return product; }
  public void setProduct(Product product) {
    this.product = product;
    this.unitPrice = BigDecimal.valueOf(product.getPrice());
    this.taxRate = product.getTaxRate() != null ? product.getTaxRate().getRate() : BigDecimal.ZERO;
    calculateTax();
  }
  public Integer getQuantity() { return quantity; }
  public BigDecimal getUnitPrice() { return unitPrice; }
  public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; calculateTax(); }
  public BigDecimal getTaxRate() { return taxRate; }
  public BigDecimal getTaxAmount() { return taxAmount; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

}
