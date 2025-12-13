package com.pos.backend.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderCreateWrapper {

  @NotNull
  @Valid
  private OrderCreateRequest order;

  @NotNull
  @Valid
  private List<OrderItemCreateRequest> items;

  public OrderCreateRequest getOrder() {
    return order;
  }

  public void setOrder(OrderCreateRequest order) {
    this.order = order;
  }

  public List<OrderItemCreateRequest> getItems() {
    return items;
  }

  public void setItems(List<OrderItemCreateRequest> items) {
    this.items = items;
  }
}
