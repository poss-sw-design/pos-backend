package com.pos.backend.domain.order;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.discount.Discount;
import com.pos.backend.domain.employee.Employee;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @Column(nullable = false)
  private String orderNumber;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column
  private String specialRequests;

  @Column(nullable = false)
  private OffsetDateTime orderDate = OffsetDateTime.now();

  @Column(nullable = false)
  private BigDecimal totalAmount = BigDecimal.ZERO;

  @Column(nullable = false)
  private BigDecimal taxAmount = BigDecimal.ZERO;

  @Column(nullable = false)
  private BigDecimal finalAmount = BigDecimal.ZERO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id")
  private Discount discount;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  public Order() {}

  public Order(Merchant merchant, Employee employee, String orderNumber) {
    this.merchant = merchant;
    this.employee = employee;
    this.orderNumber = orderNumber;
    this.status = OrderStatus.open;
  }

  public void addItem(OrderItem item) {
    item.setOrder(this);
    this.items.add(item);
  }

  public void removeItem(OrderItem item) {
    this.items.remove(item);
    item.setOrder(null);
  }

  public void setDiscount(Discount discount) {
    this.discount = discount;
  }

  public Long getOrderId() { return orderId; }
  public Merchant getMerchant() { return merchant; }
  public void setMerchant(Merchant merchant) { this.merchant = merchant; }
  public Employee getEmployee() { return employee; }
  public void setEmployee(Employee employee) { this.employee = employee; }
  public String getOrderNumber() { return orderNumber; }
  public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
  public OrderStatus getStatus() { return status; }
  public void setStatus(OrderStatus status) { this.status = status; }
  public String getSpecialRequests() { return specialRequests; }
  public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }
  public OffsetDateTime getOrderDate() { return orderDate; }
  public void setOrderDate(OffsetDateTime orderDate) { this.orderDate = orderDate; }
  public BigDecimal getTotalAmount() { return totalAmount; }
  public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
  public BigDecimal getTaxAmount() { return taxAmount; }
  public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
  public BigDecimal getFinalAmount() { return finalAmount; }
  public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
  public List<OrderItem> getItems() { return items; }
  public void setItems(List<OrderItem> items) { this.items = items; }
}
