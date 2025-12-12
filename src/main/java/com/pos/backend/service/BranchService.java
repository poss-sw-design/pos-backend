package com.pos.backend.service;

import com.pos.backend.domain.Branch;
import com.pos.backend.domain.Merchant;
import com.pos.backend.dto.branch.BranchCreateRequest;
import com.pos.backend.dto.branch.BranchResponse;
import com.pos.backend.repository.BranchRepository;
import com.pos.backend.repository.MerchantRepository;
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
      .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

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

    branchRepository.save(branch);

    return BranchResponse.from(branch);
  }

  public BranchResponse getBranch(Long branchId) {
    Branch branch = branchRepository.findById(branchId)
      .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

    return BranchResponse.from(branch);
  }
}
