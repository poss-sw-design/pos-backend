package com.pos.backend.dto.order;

import com.pos.backend.domain.order.Order;
import com.pos.backend.domain.order.OrderItem;
import com.pos.backend.domain.order.OrderStatus;

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
  private Integer totalAmount;
  private Integer finalAmount;
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
    r.totalAmount = order.getTotalAmount();
    r.finalAmount = order.getFinalAmount();
    r.items = order.getItems().stream()
      .map(OrderItemResponse::from)
      .collect(Collectors.toList());
    return r;
  }

  public Long getOrderId() { return orderId; }
  public Long getMerchantId() { return merchantId; }
  public Long getEmployeeId() { return employeeId; }
  public String getOrderNumber() { return orderNumber; }
  public OrderStatus getStatus() { return status; }
  public String getSpecialRequests() { return specialRequests; }
  public OffsetDateTime getOrderDate() { return orderDate; }
  public Integer getTotalAmount() { return totalAmount; }
  public Integer getFinalAmount() { return finalAmount; }
  public List<OrderItemResponse> getItems() { return items; }

  // --- OrderItemResponse inner class ---
  public static class OrderItemResponse {
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private Integer unitPrice;

    public static OrderItemResponse from(OrderItem item) {
      OrderItemResponse r = new OrderItemResponse();
      r.orderItemId = item.getOrderItemId();
      r.productId = item.getProduct().getProductId();
      r.quantity = item.getQuantity();
      r.unitPrice = item.getUnitPrice();
      return r;
    }

    public Long getOrderItemId() { return orderItemId; }
    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
    public Integer getUnitPrice() { return unitPrice; }
  }
}
