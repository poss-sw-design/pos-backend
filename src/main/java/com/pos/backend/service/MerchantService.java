package com.pos.backend.service;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.MerchantStatus;
import com.pos.backend.dto.merchant.MerchantCreateRequest;
import com.pos.backend.dto.merchant.MerchantResponse;
import com.pos.backend.dto.merchant.MerchantUpdateRequest;
import com.pos.backend.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MerchantService {

  private final MerchantRepository merchantRepository;

  public MerchantService(MerchantRepository merchantRepository) {
    this.merchantRepository = merchantRepository;
  }

  @Transactional
  public MerchantResponse createMerchant(MerchantCreateRequest req) {
    Merchant merchant = new Merchant(
      req.getBusinessName(),
      req.getBusinessType(),
      req.getAddressLine1(),
      req.getCity(),
      req.getRegion(),
      req.getPostalCode(),
      req.getPhone(),
      req.getEmail()
    );

    Merchant saved = merchantRepository.save(merchant);
    return MerchantResponse.from(saved);
  }

  @Transactional(readOnly = true)
  public MerchantResponse getMerchant(Long id) {
    Merchant merchant = merchantRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Merchant not found"));
    return MerchantResponse.from(merchant);
  }

  @Transactional
  public MerchantResponse updateMerchant(Long id, MerchantUpdateRequest req) {
    Merchant merchant = merchantRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Merchant not found"));

    if (req.getBusinessName() != null) merchant.setBusinessName(req.getBusinessName());
    if (req.getBusinessType() != null) {
      merchant.setBusinessType(req.getBusinessType());
    }
    if (req.getPhone() != null) merchant.setPhone(req.getPhone());
    if (req.getEmail() != null) merchant.setEmail(req.getEmail());
    if (req.getStatus() != null)
      merchant.setStatus(MerchantStatus.valueOf(req.getStatus()));
    if (req.getAddressLine1() != null) merchant.setAddressLine1(req.getAddressLine1());

    merchant.setUpdatedAt(java.time.OffsetDateTime.now());

    return MerchantResponse.from(merchant);
  }

  @Transactional
  public void deleteMerchant(Long id) {
    if (!merchantRepository.existsById(id)) {
      throw new EntityNotFoundException("Merchant not found");
    }
    merchantRepository.deleteById(id);
  }
}
