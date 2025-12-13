package com.pos.backend.service;

import com.pos.backend.domain.discount.Discount;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.order.Order;
import com.pos.backend.domain.order.OrderItem;
import com.pos.backend.domain.order.OrderStatus;
import com.pos.backend.domain.product.Product;
import com.pos.backend.domain.Merchant;
import com.pos.backend.dto.order.*;
import com.pos.backend.repository.DiscountRepository;
import com.pos.backend.repository.EmployeeRepository;
import com.pos.backend.repository.OrderRepository;
import com.pos.backend.repository.ProductRepository;
import com.pos.backend.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final EmployeeRepository employeeRepository;
  private final MerchantRepository merchantRepository;
  private final ProductRepository productRepository;
  private final DiscountRepository discountRepository;

  public OrderService(OrderRepository orderRepository,
                      EmployeeRepository employeeRepository,
                      MerchantRepository merchantRepository,
                      ProductRepository productRepository,
                      DiscountRepository discountRepository) {
    this.orderRepository = orderRepository;
    this.employeeRepository = employeeRepository;
    this.merchantRepository = merchantRepository;
    this.productRepository = productRepository;
    this.discountRepository = discountRepository;
  }

  @Transactional
  public OrderResponse createOrder(OrderCreateRequest request, List<OrderItemCreateRequest> items) {
    Merchant merchant = merchantRepository.findById(request.getMerchantId())
      .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));
    Employee employee = employeeRepository.findById(request.getEmployeeId())
      .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

    Order order = new Order(merchant, employee, request.getOrderNumber());

    for (OrderItemCreateRequest itemReq : items) {
      Product product = productRepository.findById(itemReq.getProductId())
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
      OrderItem orderItem = new OrderItem(product, itemReq.getQuantity(), itemReq.getUnitPrice());
      order.addItem(orderItem);
    }

    orderRepository.save(order);
    return OrderResponse.from(order);
  }

  @Transactional(readOnly = true)
  public OrderResponse getOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    return OrderResponse.from(order);
  }

  @Transactional
  public OrderResponse updateOrder(Long orderId, OrderUpdateRequest request) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    if (request.getStatus() != null) order.setStatus(request.getStatus());
    if (request.getSpecialRequests() != null) order.setSpecialRequests(request.getSpecialRequests());

    if (request.getDiscountId() != null) {
      Discount discount = discountRepository.findById(request.getDiscountId())
        .orElseThrow(() -> new IllegalArgumentException("Discount not found"));
      order.setDiscount(discount);
    }

    orderRepository.save(order);
    return OrderResponse.from(order);
  }

  @Transactional
  public void deleteOrder(Long orderId) {
    if (!orderRepository.existsById(orderId)) {
      throw new IllegalArgumentException("Order not found");
    }
    orderRepository.deleteById(orderId);
  }

  @Transactional
  public OrderResponse addOrderItem(Long orderId, OrderItemCreateRequest itemReq) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    Product product = productRepository.findById(itemReq.getProductId())
      .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    OrderItem item = new OrderItem(product, itemReq.getQuantity(), itemReq.getUnitPrice());
    order.addItem(item);

    orderRepository.save(order);
    return OrderResponse.from(order);
  }

  @Transactional
  public OrderResponse updateOrderItem(Long orderId, Long orderItemId, OrderItemUpdateRequest request) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    OrderItem item = order.getItems().stream()
      .filter(i -> i.getOrderItemId().equals(orderItemId))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

    if (request.getQuantity() != null) item.setQuantity(request.getQuantity());
    if (request.getUnitPrice() != null) item.setUnitPrice(request.getUnitPrice());

    order.recalculateAmounts();
    orderRepository.save(order);
    return OrderResponse.from(order);
  }

  @Transactional
  public OrderResponse removeOrderItem(Long orderId, Long orderItemId) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    OrderItem item = order.getItems().stream()
      .filter(i -> i.getOrderItemId().equals(orderItemId))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

    order.removeItem(item);
    orderRepository.save(order);
    return OrderResponse.from(order);
  }
}
