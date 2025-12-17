package com.pos.backend.dto.order;

import com.pos.backend.domain.order.Order;
import com.pos.backend.domain.order.OrderItem;
import com.pos.backend.domain.order.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {

  private Long orderId;
  private Long merchantId;
  private Long employeeId;
  private String orderNumber;
  private OrderStatus status;
  private String specialRequests;
  private OffsetDateTime orderDate;
  private BigDecimal totalAmount;
  private BigDecimal taxAmount;
  private BigDecimal finalAmount;
  private List<OrderItemResponse> items;

  public static OrderResponse from(Order order) {
    OrderResponse r = new OrderResponse();
    r.orderId = order.getOrderId();
    r.merchantId = order.getMerchant().getMerchantId();
    r.employeeId = order.getEmployee().getEmployeeId();
    r.orderNumber = order.getOrderNumber();
    r.status = order.getStatus();
    r.specialRequests = order.getSpecialRequests();
    r.orderDate = order.getOrderDate();

    r.totalAmount = order.getTotalAmount() != null
      ? order.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP)
      : BigDecimal.ZERO.setScale(2);

    r.taxAmount = order.getTaxAmount() != null
      ? order.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP)
      : BigDecimal.ZERO.setScale(2);

    r.finalAmount = order.getFinalAmount() != null
      ? order.getFinalAmount().setScale(2, BigDecimal.ROUND_HALF_UP)
      : BigDecimal.ZERO.setScale(2);

    r.items = order.getItems().stream()
      .map(OrderItemResponse::from)
      .collect(Collectors.toList());

    return r;
  }

  public Long getOrderId() { return orderId; }
  public void setOrderId(Long orderId) { this.orderId = orderId; }

  public Long getMerchantId() { return merchantId; }
  public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }

  public Long getEmployeeId() { return employeeId; }
  public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

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

  public List<OrderItemResponse> getItems() { return items; }
  public void setItems(List<OrderItemResponse> items) { this.items = items; }

  public static class OrderItemResponse {
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal taxRate;
    private BigDecimal taxAmount;

    public static OrderItemResponse from(OrderItem item) {
      OrderItemResponse r = new OrderItemResponse();
      r.orderItemId = item.getOrderItemId();
      r.productId = item.getProduct().getProductId();
      r.quantity = item.getQuantity();
      r.unitPrice = item.getUnitPrice() != null
        ? item.getUnitPrice().setScale(2, BigDecimal.ROUND_HALF_UP)
        : BigDecimal.ZERO.setScale(2);
      r.taxRate = item.getTaxRate() != null
        ? item.getTaxRate().setScale(2, BigDecimal.ROUND_HALF_UP)
        : BigDecimal.ZERO.setScale(2);
      r.taxAmount = item.getTaxAmount() != null
        ? item.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP)
        : BigDecimal.ZERO.setScale(2);
      return r;
    }

    public Long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Long orderItemId) { this.orderItemId = orderItemId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public BigDecimal getTaxRate() { return taxRate; }
    public void setTaxRate(BigDecimal taxRate) { this.taxRate = taxRate; }

    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
  }
}
