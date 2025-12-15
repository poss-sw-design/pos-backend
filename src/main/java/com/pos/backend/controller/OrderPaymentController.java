package com.pos.backend.controller;

import com.pos.backend.dto.order.OrderCreateRequest;
import com.pos.backend.dto.order.OrderItemCreateRequest;
import com.pos.backend.dto.payment.PaymentCreateRequest;
import com.pos.backend.dto.payment.PaymentResponse;
import com.pos.backend.service.OrderPaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderPaymentController {

  private final OrderPaymentService orderPaymentService;

  public OrderPaymentController(OrderPaymentService orderPaymentService) {
    this.orderPaymentService = orderPaymentService;
  }

  @PostMapping("/create-with-payment")
  public ResponseEntity<PaymentResponse> createOrderWithPayment(
    @Valid @RequestBody OrderPaymentRequest request
  ) {
    OrderCreateRequest orderReq = request.getOrder();

    List<OrderItemCreateRequest> itemsReq = orderReq.getItems();

    PaymentResponse response = orderPaymentService.createOrderWithItemsDiscountAndPayment(
      orderReq,
      itemsReq,
      request.getDiscountId(),
      request.getPayment()
    );
    return ResponseEntity.ok(response);
  }

  public static class OrderPaymentRequest {

    @NotNull
    private OrderCreateRequest order;

    private Long discountId;

    @NotNull
    private PaymentCreateRequest payment;

    public OrderCreateRequest getOrder() { return order; }
    public void setOrder(OrderCreateRequest order) { this.order = order; }

    public Long getDiscountId() { return discountId; }
    public void setDiscountId(Long discountId) { this.discountId = discountId; }

    public PaymentCreateRequest getPayment() { return payment; }
    public void setPayment(PaymentCreateRequest payment) { this.payment = payment; }
  }
}
