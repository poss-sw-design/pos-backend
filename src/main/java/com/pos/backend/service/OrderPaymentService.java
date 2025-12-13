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

  public OrderPaymentService(OrderRepository orderRepository,
                             ProductRepository productRepository,
                             DiscountRepository discountRepository,
                             PaymentRepository paymentRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.discountRepository = discountRepository;
    this.paymentRepository = paymentRepository;
  }

  @Transactional
  public PaymentResponse createOrderWithItemsDiscountAndPayment(
    OrderCreateRequest orderReq,
    List<OrderItemCreateRequest> itemsReq,
    Long discountId,
    PaymentCreateRequest paymentReq
  ) {

    Order order = new Order();
    order.setOrderNumber(orderReq.getOrderNumber());
    order.setMerchant(orderRepository.getReferenceById(orderReq.getMerchantId()).getMerchant());
    order.setEmployee(orderRepository.getReferenceById(orderReq.getEmployeeId()).getEmployee());

    for (OrderItemCreateRequest itemReq : itemsReq) {
      OrderItem item = new OrderItem(
        productRepository.getReferenceById(itemReq.getProductId()),
        itemReq.getQuantity(),
        itemReq.getUnitPrice()
      );
      order.addItem(item);
    }

    if (discountId != null) {
      Discount discount = discountRepository.findById(discountId)
        .orElseThrow(() -> new IllegalArgumentException("Discount not found"));

      if (order.getTotalAmount() >= discount.getMinimumOrderValue().intValue()) {
        BigDecimal discountAmount = calculateDiscount(order.getTotalAmount(), discount);
        order.setDiscount(discount);
        order.setFinalAmount(order.getTotalAmount() - discountAmount.intValue());
      }
    }

    orderRepository.save(order);

    Payment payment = new Payment();
    payment.setOrder(order);
    payment.setMerchant(order.getMerchant());
    payment.setPaymentMethod(paymentReq.getPaymentMethod());
    payment.setSplit(paymentReq.getSplit() != null ? paymentReq.getSplit() : false);
    payment.setTipAmount(paymentReq.getTipAmount() != null ? paymentReq.getTipAmount() : BigDecimal.ZERO);
    payment.setStatus(PaymentStatus.pending);

    paymentRepository.save(payment);

    payment.setStatus(PaymentStatus.completed);
    payment.setProcessedAt(OffsetDateTime.now());
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

  //
}
