package com.pos.backend.service;

import com.pos.backend.domain.Branch;
import com.pos.backend.domain.Merchant;
import com.pos.backend.dto.branch.BranchCreateRequest;
import com.pos.backend.dto.branch.BranchResponse;
import com.pos.backend.dto.branch.BranchUpdateRequest;
import com.pos.backend.repository.BranchRepository;
import com.pos.backend.repository.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BranchService {

  private final BranchRepository branchRepository;
  private final MerchantRepository merchantRepository;

  public BranchService(BranchRepository branchRepository, MerchantRepository merchantRepository) {
    this.branchRepository = branchRepository;
    this.merchantRepository = merchantRepository;
  }

  @Transactional
  public BranchResponse createBranch(BranchCreateRequest req) {

    Merchant merchant = merchantRepository.findById(req.getMerchantId())
      .orElseThrow(() -> new EntityNotFoundException("Merchant not found"));

    Branch branch = new Branch(
      merchant,
      req.getName(),
      req.getAddressLine1(),
      req.getCity(),
      req.getRegion(),
      req.getPostalCode(),
      req.getPhone(),
      req.getEmail()
    );

    Branch saved = branchRepository.save(branch);
    return BranchResponse.from(saved);
  }

  @Transactional(readOnly = true)
  public BranchResponse getBranch(Long id) {
    Branch branch = branchRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
    return BranchResponse.from(branch);
  }

  @Transactional
  public BranchResponse updateBranch(Long id, BranchUpdateRequest req) {
    Branch branch = branchRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

    if (req.getName() != null) branch.setName(req.getName());
    if (req.getAddressLine1() != null) branch.setAddressLine1(req.getAddressLine1());
    if (req.getCity() != null) branch.setCity(req.getCity());
    if (req.getRegion() != null) branch.setRegion(req.getRegion());
    if (req.getPostalCode() != null) branch.setPostalCode(req.getPostalCode());
    if (req.getPhone() != null) branch.setPhone(req.getPhone());
    if (req.getEmail() != null) branch.setEmail(req.getEmail());
    if (req.getStatus() != null) branch.setStatus(req.getStatus());

    branch.setUpdatedAt(java.time.OffsetDateTime.now());

    return BranchResponse.from(branch);
  }

  @Transactional
  public void deleteBranch(Long id) {
    if (!branchRepository.existsById(id)) {
      throw new EntityNotFoundException("Branch not found");
    }
    branchRepository.deleteById(id);
  }
}
