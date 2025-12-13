package com.pos.backend.service;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.order.Order;
import com.pos.backend.domain.payment.Payment;
import com.pos.backend.dto.payment.PaymentCreateRequest;
import com.pos.backend.dto.payment.PaymentResponse;
import com.pos.backend.dto.payment.PaymentUpdateRequest;
import com.pos.backend.repository.MerchantRepository;
import com.pos.backend.repository.OrderRepository;
import com.pos.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final OrderRepository orderRepository;
  private final MerchantRepository merchantRepository;

  public PaymentService(PaymentRepository paymentRepository,
                        OrderRepository orderRepository,
                        MerchantRepository merchantRepository) {
    this.paymentRepository = paymentRepository;
    this.orderRepository = orderRepository;
    this.merchantRepository = merchantRepository;
  }

  public PaymentResponse createPayment(PaymentCreateRequest request) {
    Order order = orderRepository.findById(request.getOrderId())
      .orElseThrow(() -> new RuntimeException("Order not found: " + request.getOrderId()));

    Merchant merchant = order.getMerchant();

    Payment payment = new Payment();
    payment.setOrder(order);
    payment.setPaymentMethod(request.getPaymentMethod());
    payment.setSplit(request.getSplit());
    payment.setTipAmount(request.getTipAmount());
    // PaymentStatus initial value
    payment.setStatus(com.pos.backend.domain.payment.PaymentStatus.pending);

    Payment saved = paymentRepository.save(payment);
    return PaymentResponse.from(saved);
  }

  public List<PaymentResponse> getAllPayments() {
    return paymentRepository.findAll()
      .stream()
      .map(PaymentResponse::from)
      .collect(Collectors.toList());
  }

  public PaymentResponse getPaymentById(Long id) {
    Payment payment = paymentRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
    return PaymentResponse.from(payment);
  }

  public PaymentResponse updatePayment(Long id, PaymentUpdateRequest request) {
    Payment payment = paymentRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Payment not found: " + id));

    if (request.getPaymentMethod() != null) payment.setPaymentMethod(request.getPaymentMethod());
    if (request.getSplit() != null) payment.setSplit(request.getSplit());
    if (request.getTipAmount() != null) payment.setTipAmount(request.getTipAmount());

    Payment saved = paymentRepository.save(payment);
    return PaymentResponse.from(saved);
  }

  public void deletePayment(Long id) {
    Payment payment = paymentRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
    paymentRepository.delete(payment);
  }
}
