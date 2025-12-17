package com.pos.backend.service;

import com.pos.backend.domain.discount.Discount;
import com.pos.backend.domain.discount.DiscountValueType;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.order.*;
import com.pos.backend.domain.payment.Payment;
import com.pos.backend.domain.payment.PaymentStatus;
import com.pos.backend.domain.product.Product;
import com.pos.backend.dto.order.OrderCreateRequest;
import com.pos.backend.dto.order.OrderItemCreateRequest;
import com.pos.backend.dto.payment.PaymentCreateRequest;
import com.pos.backend.dto.payment.PaymentResponse;
import com.pos.backend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderPaymentService {

  private final OrderRepository orderRepository;
  private final DiscountRepository discountRepository;
  private final PaymentRepository paymentRepository;

  public OrderPaymentService(OrderRepository orderRepository,
                             DiscountRepository discountRepository,
                             PaymentRepository paymentRepository) {
    this.orderRepository = orderRepository;
    this.discountRepository = discountRepository;
    this.paymentRepository = paymentRepository;
  }

  @Transactional
  public PaymentResponse payOrder(
    Long orderId,
    Long discountId,
    PaymentCreateRequest paymentReq
  ) {

    Order order = orderRepository.findById(orderId)
      .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    if (order.getItems().isEmpty()) {
      throw new IllegalStateException("Cannot pay empty order");
    }

    if (discountId != null) {
      Discount discount = discountRepository.findById(discountId)
        .orElseThrow(() -> new IllegalArgumentException("Discount not found"));

      BigDecimal discountAmount = calculateDiscount(order.getFinalAmount(), discount);
      order.setFinalAmount(order.getFinalAmount().subtract(discountAmount));
      order.setDiscount(discount);
    }

    Payment payment = new Payment();
    payment.setOrder(order);
    payment.setMerchant(order.getMerchant());
    payment.setPaymentMethod(paymentReq.getPaymentMethod());
    payment.setSplit(Boolean.TRUE.equals(paymentReq.getSplit()));
    payment.setTipAmount(
      paymentReq.getTipAmount() != null ? paymentReq.getTipAmount() : BigDecimal.ZERO
    );
    payment.setStatus(PaymentStatus.completed);
    payment.setProcessedAt(OffsetDateTime.now());

    paymentRepository.save(payment);

    order.setStatus(OrderStatus.completed);

    return PaymentResponse.from(payment);
  }

  private BigDecimal calculateDiscount(BigDecimal amount, Discount discount) {
    if (discount.getValueType() == DiscountValueType.percentage) {
      return amount.multiply(discount.getValue())
        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
    return discount.getValue().setScale(2, RoundingMode.HALF_UP);
  }
}
