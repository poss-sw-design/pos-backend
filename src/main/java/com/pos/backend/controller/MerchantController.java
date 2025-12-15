package com.pos.backend.controller;

import com.pos.backend.dto.merchant.MerchantCreateRequest;
import com.pos.backend.dto.merchant.MerchantResponse;
import com.pos.backend.dto.merchant.MerchantUpdateRequest;
import com.pos.backend.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

  private final MerchantService merchantService;

  public MerchantController(MerchantService merchantService) {
    this.merchantService = merchantService;
  }

  @PostMapping
  public ResponseEntity<MerchantResponse> create(@Valid @RequestBody MerchantCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(merchantService.createMerchant(req));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MerchantResponse> get(@PathVariable Long id) {
    return ResponseEntity.ok(merchantService.getMerchant(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<MerchantResponse> update(
    @PathVariable Long id,
    @Valid @RequestBody MerchantUpdateRequest req) {
    return ResponseEntity.ok(merchantService.updateMerchant(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    merchantService.deleteMerchant(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<MerchantResponse>> getAllMerchants() {
    return ResponseEntity.ok(merchantService.getAllMerchants());
  }
}
