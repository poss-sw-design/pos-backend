package com.pos.backend.service;

import com.pos.backend.domain.discount.Discount;
import com.pos.backend.domain.discount.DiscountValueType;
import com.pos.backend.domain.order.*;
import com.pos.backend.domain.payment.Payment;
import com.pos.backend.domain.payment.PaymentStatus;
import com.pos.backend.dto.order.OrderCreateRequest;
import com.pos.backend.dto.order.OrderItemCreateRequest;
import com.pos.backend.dto.payment.PaymentCreateRequest;
import com.pos.backend.dto.payment.PaymentResponse;
import com.pos.backend.repository.DiscountRepository;
import com.pos.backend.repository.EmployeeRepository;
import com.pos.backend.repository.MerchantRepository;
import com.pos.backend.repository.OrderRepository;
import com.pos.backend.repository.PaymentRepository;
import com.pos.backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderPaymentService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final DiscountRepository discountRepository;
  private final PaymentRepository paymentRepository;
  private final MerchantRepository merchantRepository;
  private final EmployeeRepository employeeRepository;

  public OrderPaymentService(OrderRepository orderRepository,
                             ProductRepository productRepository,
                             DiscountRepository discountRepository,
                             PaymentRepository paymentRepository,
                             MerchantRepository merchantRepository,
                             EmployeeRepository employeeRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.discountRepository = discountRepository;
    this.paymentRepository = paymentRepository;
    this.merchantRepository = merchantRepository;
    this.employeeRepository = employeeRepository;
  }

  @Transactional
  public PaymentResponse createOrderWithItemsDiscountAndPayment(
    OrderCreateRequest orderReq,
    List<OrderItemCreateRequest> itemsReq,
    Long discountId,
    PaymentCreateRequest paymentReq
  ) {

    // Order 생성 및 기본 status 설정
    Order order = new Order();
    order.setOrderNumber(orderReq.getOrderNumber());
    order.setStatus(OrderStatus.open); // 기본값 설정

    // Merchant 조회
    order.setMerchant(
      merchantRepository.findById(orderReq.getMerchantId())
        .orElseThrow(() -> new IllegalArgumentException("Merchant not found"))
    );

    // Employee 조회
    order.setEmployee(
      employeeRepository.findById(orderReq.getEmployeeId())
        .orElseThrow(() -> new IllegalArgumentException("Employee not found"))
    );

    // OrderItem 처리
    if (itemsReq != null) {
      for (OrderItemCreateRequest itemReq : itemsReq) {
        order.addItem(
          new OrderItem(
            productRepository.findById(itemReq.getProductId())
              .orElseThrow(() -> new IllegalArgumentException("Product not found: " + itemReq.getProductId())),
            itemReq.getQuantity(),
            itemReq.getUnitPrice()
          )
        );
      }
    }

    // Discount 처리
    if (discountId != null) {
      Discount discount = discountRepository.findById(discountId)
        .orElseThrow(() -> new IllegalArgumentException("Discount not found"));

      if (order.getTotalAmount() >= discount.getMinimumOrderValue().intValue()) {
        BigDecimal discountAmount = calculateDiscount(order.getTotalAmount(), discount);
        order.setDiscount(discount);
        order.setFinalAmount(order.getTotalAmount() - discountAmount.intValue());
      } else {
        order.setFinalAmount(order.getTotalAmount());
      }
    } else {
      order.setFinalAmount(order.getTotalAmount());
    }

    // Order 저장
    orderRepository.save(order);

    // Payment 처리
    Payment payment = new Payment();
    payment.setOrder(order);
    payment.setMerchant(order.getMerchant());
    payment.setPaymentMethod(paymentReq.getPaymentMethod());
    payment.setSplit(paymentReq.getSplit() != null ? paymentReq.getSplit() : false);
    payment.setTipAmount(paymentReq.getTipAmount() != null ? paymentReq.getTipAmount() : BigDecimal.ZERO);
    payment.setStatus(PaymentStatus.pending);

    paymentRepository.save(payment);

    // 결제 완료 처리
    payment.setStatus(PaymentStatus.completed);
    payment.setProcessedAt(OffsetDateTime.now());

    // Order 상태 업데이트
    order.setStatus(OrderStatus.completed);

    return PaymentResponse.from(payment);
  }

  private BigDecimal calculateDiscount(int totalAmount, Discount discount) {
    if (discount.getValueType() == DiscountValueType.percentage) {
      return BigDecimal.valueOf(totalAmount)
        .multiply(discount.getValue())
        .divide(BigDecimal.valueOf(100));
    } else {
      return discount.getValue();
    }
  }
}
