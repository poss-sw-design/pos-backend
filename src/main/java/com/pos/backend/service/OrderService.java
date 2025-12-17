package com.pos.backend.service;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.discount.Discount;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.order.*;
import com.pos.backend.domain.product.Product;
import com.pos.backend.dto.order.*;
import com.pos.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final EmployeeRepository employeeRepository;
  private final MerchantRepository merchantRepository;
  private final ProductRepository productRepository;
  private final DiscountRepository discountRepository;

  public OrderService(
    OrderRepository orderRepository,
    EmployeeRepository employeeRepository,
    MerchantRepository merchantRepository,
    ProductRepository productRepository,
    DiscountRepository discountRepository
  ) {
    this.orderRepository = orderRepository;
    this.employeeRepository = employeeRepository;
    this.merchantRepository = merchantRepository;
    this.productRepository = productRepository;
    this.discountRepository = discountRepository;
  }

  private void calculateAmounts(Order order) {
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal taxTotal = BigDecimal.ZERO;

    for (OrderItem item : order.getItems()) {
      BigDecimal itemSubtotal =
        item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

      total = total.add(itemSubtotal);
      taxTotal = taxTotal.add(item.getTaxAmount());
    }

    order.setTotalAmount(total.setScale(2, RoundingMode.HALF_UP));
    order.setTaxAmount(taxTotal.setScale(2, RoundingMode.HALF_UP));
    order.setFinalAmount(
      total.add(taxTotal).setScale(2, RoundingMode.HALF_UP)
    );
  }


  @Transactional
  public OrderResponse createOrder(OrderCreateRequest request) {
    Merchant merchant = merchantRepository.findById(request.getMerchantId())
      .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

    Employee employee = employeeRepository.findById(request.getEmployeeId())
      .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

    Order order = new Order(merchant, employee, request.getOrderNumber());

    if (request.getItems() != null) {
      for (OrderItemCreateRequest itemReq : request.getItems()) {
        Product product = productRepository.findById(itemReq.getProductId())
          .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        order.addItem(new OrderItem(product, itemReq.getQuantity()));
      }
    }

    calculateAmounts(order);

    orderRepository.save(order);
    return OrderResponse.from(order);
  }

  @Transactional
  public OrderResponse addOrderItem(Long orderId, OrderItemCreateRequest request) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    Product product = productRepository.findById(request.getProductId())
      .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    order.addItem(new OrderItem(product, request.getQuantity()));
    calculateAmounts(order);

    return OrderResponse.from(order);
  }

  @Transactional
  public OrderResponse updateOrderItem(
    Long orderId,
    Long orderItemId,
    OrderItemUpdateRequest request
  ) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    OrderItem item = order.getItems().stream()
      .filter(i -> i.getOrderItemId().equals(orderItemId))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));

    if (request.getQuantity() != null) {
      item.setQuantity(request.getQuantity());
    }

    calculateAmounts(order);
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
    calculateAmounts(order);

    return OrderResponse.from(order);
  }

  @Transactional(readOnly = true)
  public OrderResponse getOrder(Long orderId) {
    return orderRepository.findById(orderId)
      .map(OrderResponse::from)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));
  }

  @Transactional
  public OrderResponse updateOrder(Long orderId, OrderUpdateRequest request) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    if (request.getStatus() != null) {
      order.setStatus(request.getStatus());
    }

    if (request.getSpecialRequests() != null) {
      order.setSpecialRequests(request.getSpecialRequests());
    }

    return OrderResponse.from(order);
  }

  @Transactional
  public void deleteOrder(Long orderId) {
    if (!orderRepository.existsById(orderId)) {
      throw new IllegalArgumentException("Order not found");
    }
    orderRepository.deleteById(orderId);
  }

  public List<OrderResponse> getAllOrders() {
    return orderRepository.findAll().stream()
      .map(OrderResponse::from)
      .collect(Collectors.toList());
  }
}
