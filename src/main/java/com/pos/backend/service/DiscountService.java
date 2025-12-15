package com.pos.backend.service;

import com.pos.backend.domain.discount.Discount;
import com.pos.backend.dto.discount.DiscountCreateRequest;
import com.pos.backend.dto.discount.DiscountResponse;
import com.pos.backend.dto.discount.DiscountUpdateRequest;
import com.pos.backend.repository.DiscountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiscountService {

  private final DiscountRepository discountRepository;

  public DiscountService(DiscountRepository discountRepository) {
    this.discountRepository = discountRepository;
  }

  public DiscountResponse createDiscount(DiscountCreateRequest request) {
    Discount discount = new Discount();
    discount.setName(request.getName());
    discount.setType(request.getType());
    discount.setValueType(request.getValueType());
    discount.setValue(request.getValue());
    discount.setMinimumOrderValue(request.getMinimumOrderValue());
    discount.setStartTime(request.getStartTime());
    discount.setEndTime(request.getEndTime());

    Discount saved = discountRepository.save(discount);
    return DiscountResponse.from(saved);
  }

  public List<DiscountResponse> getAllDiscounts() {
    return discountRepository.findAll()
      .stream()
      .map(DiscountResponse::from)
      .collect(Collectors.toList());
  }

  public DiscountResponse getDiscountById(Long id) {
    Discount discount = discountRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Discount not found: " + id));
    return DiscountResponse.from(discount);
  }

  public DiscountResponse updateDiscount(Long id, DiscountUpdateRequest request) {
    Discount discount = discountRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Discount not found: " + id));

    if (request.getName() != null) discount.setName(request.getName());
    if (request.getType() != null) discount.setType(request.getType());
    if (request.getValueType() != null) discount.setValueType(request.getValueType());
    if (request.getValue() != null) discount.setValue(request.getValue());
    if (request.getMinimumOrderValue() != null) discount.setMinimumOrderValue(request.getMinimumOrderValue());
    if (request.getStatus() != null) discount.setStatus(request.getStatus());
    if (request.getStartTime() != null) discount.setStartTime(request.getStartTime());
    if (request.getEndTime() != null) discount.setEndTime(request.getEndTime());

    Discount saved = discountRepository.save(discount);
    return DiscountResponse.from(saved);
  }

  public void deleteDiscount(Long id) {
    Discount discount = discountRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Discount not found: " + id));
    discountRepository.delete(discount);
  }
}
