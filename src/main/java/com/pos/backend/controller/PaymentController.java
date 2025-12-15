package com.pos.backend.controller;

import com.pos.backend.dto.payment.PaymentCreateRequest;
import com.pos.backend.dto.payment.PaymentResponse;
import com.pos.backend.dto.payment.PaymentUpdateRequest;
import com.pos.backend.dto.product.ProductResponse;
import com.pos.backend.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PaymentResponse createPayment(@RequestBody PaymentCreateRequest request) {
    return paymentService.createPayment(request);
  }

  @GetMapping
  public List<PaymentResponse> getAllPayments() {
    return paymentService.getAllPayments();
  }

  @GetMapping("/{id}")
  public PaymentResponse getPayment(@PathVariable Long id) {
    return paymentService.getPaymentById(id);
  }

  @PutMapping("/{id}")
  public PaymentResponse updatePayment(@PathVariable Long id,
                                       @RequestBody PaymentUpdateRequest request) {
    return paymentService.updatePayment(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePayment(@PathVariable Long id) {
    paymentService.deletePayment(id);
  }

}
