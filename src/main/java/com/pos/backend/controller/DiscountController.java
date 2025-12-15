package com.pos.backend.controller;

import com.pos.backend.dto.discount.DiscountCreateRequest;
import com.pos.backend.dto.discount.DiscountResponse;
import com.pos.backend.dto.discount.DiscountUpdateRequest;
import com.pos.backend.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

  private final DiscountService discountService;

  public DiscountController(DiscountService discountService) {
    this.discountService = discountService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DiscountResponse createDiscount(@RequestBody DiscountCreateRequest request) {
    return discountService.createDiscount(request);
  }

  @GetMapping
  public List<DiscountResponse> getAllDiscounts() {
    return discountService.getAllDiscounts();
  }

  @GetMapping("/{id}")
  public DiscountResponse getDiscount(@PathVariable Long id) {
    return discountService.getDiscountById(id);
  }

  @PutMapping("/{id}")
  public DiscountResponse updateDiscount(@PathVariable Long id,
                                         @RequestBody DiscountUpdateRequest request) {
    return discountService.updateDiscount(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDiscount(@PathVariable Long id) {
    discountService.deleteDiscount(id);
  }
}
