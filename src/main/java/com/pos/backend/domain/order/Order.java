package com.pos.backend.domain.order;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.discount.Discount;
import jakarta.persistence.*;

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

  @Column(nullable = false, length = 50, unique = true)
  private String orderNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id")
  private Discount discount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private OrderStatus status;

  @Column(columnDefinition = "TEXT")
  private String specialRequests;

  @Column(nullable = false)
  private OffsetDateTime orderDate = OffsetDateTime.now();

  @Column(nullable = false)
  private Integer totalAmount = 0;

  @Column(nullable = false)
  private Integer finalAmount = 0;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  protected Order() {}

  public Order(Merchant merchant, Employee employee, String orderNumber) {
    this.merchant = merchant;
    this.employee = employee;
    this.orderNumber = orderNumber;
    this.status = OrderStatus.OPEN;
  }

  public void addItem(OrderItem item) {
    items.add(item);
    item.setOrder(this);
    recalculateAmounts();
  }

  public void removeItem(OrderItem item) {
    items.remove(item);
    item.setOrder(null);
    recalculateAmounts();
  }

  public void recalculateAmounts() {
    this.totalAmount = items.stream()
      .mapToInt(i -> i.getUnitPrice() * i.getQuantity())
      .sum();
    this.finalAmount = this.totalAmount;
  }

  public Long getOrderId() { return orderId; }
  public Merchant getMerchant() { return merchant; }
  public Employee getEmployee() { return employee; }
  public String getOrderNumber() { return orderNumber; }
  public Discount getDiscount() { return discount; }
  public void setDiscount(Discount discount) { this.discount = discount; }
  public OrderStatus getStatus() { return status; }
  public void setStatus(OrderStatus status) { this.status = status; }
  public String getSpecialRequests() { return specialRequests; }
  public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }
  public OffsetDateTime getOrderDate() { return orderDate; }
  public Integer getTotalAmount() { return totalAmount; }
  public Integer getFinalAmount() { return finalAmount; }
  public List<OrderItem> getItems() { return items; }
}
