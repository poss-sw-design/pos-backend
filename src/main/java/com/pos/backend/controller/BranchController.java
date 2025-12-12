package com.pos.backend.controller;

import com.pos.backend.dto.branch.BranchCreateRequest;
import com.pos.backend.dto.branch.BranchResponse;
import com.pos.backend.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

  private final BranchService branchService;

  public BranchController(BranchService branchService) {
    this.branchService = branchService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BranchResponse create(@Valid @RequestBody BranchCreateRequest req) {
    return branchService.createBranch(req);
  }

  @GetMapping("/{branchId}")
  public BranchResponse getBranch(@PathVariable Long branchId) {
    return branchService.getBranch(branchId);
  }
}
