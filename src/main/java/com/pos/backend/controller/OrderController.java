package com.pos.backend.controller;

import com.pos.backend.dto.order.*;
import com.pos.backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResponse createOrder(@Valid @RequestBody OrderCreateRequest request,
                                   @RequestBody List<OrderItemCreateRequest> items) {
    return orderService.createOrder(request, items);
  }

  @GetMapping("/{orderId}")
  public OrderResponse getOrder(@PathVariable Long orderId) {
    return orderService.getOrder(orderId);
  }

  @PutMapping("/{orderId}")
  public OrderResponse updateOrder(@PathVariable Long orderId,
                                   @RequestBody OrderUpdateRequest request) {
    return orderService.updateOrder(orderId, request);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable Long orderId) {
    orderService.deleteOrder(orderId);
  }

  @PostMapping("/{orderId}/items")
  public OrderResponse addOrderItem(@PathVariable Long orderId,
                                    @RequestBody OrderItemCreateRequest request) {
    return orderService.addOrderItem(orderId, request);
  }

  @PutMapping("/{orderId}/items/{orderItemId}")
  public OrderResponse updateOrderItem(@PathVariable Long orderId,
                                       @PathVariable Long orderItemId,
                                       @RequestBody OrderItemUpdateRequest request) {
    return orderService.updateOrderItem(orderId, orderItemId, request);
  }

  @DeleteMapping("/{orderId}/items/{orderItemId}")
  public OrderResponse removeOrderItem(@PathVariable Long orderId,
                                       @PathVariable Long orderItemId) {
    return orderService.removeOrderItem(orderId, orderItemId);
  }
}
