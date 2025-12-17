package com.pos.backend.controller;

import com.pos.backend.dto.payment.PaymentCreateRequest;
import com.pos.backend.dto.payment.PaymentResponse;
import com.pos.backend.service.OrderPaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderPaymentController {

  private final OrderPaymentService orderPaymentService;

  public OrderPaymentController(OrderPaymentService orderPaymentService) {
    this.orderPaymentService = orderPaymentService;
  }

  @PostMapping("/{orderId}/payment")
  public ResponseEntity<PaymentResponse> payOrder(
    @PathVariable Long orderId,
    @RequestParam(required = false) Long discountId,
    @Valid @RequestBody PaymentCreateRequest paymentRequest
  ) {
    PaymentResponse response =
      orderPaymentService.payOrder(orderId, discountId, paymentRequest);

    return ResponseEntity.ok(response);
  }
}
