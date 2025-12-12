package com.pos.backend.controller;

import com.pos.backend.dto.branch.BranchCreateRequest;
import com.pos.backend.dto.branch.BranchResponse;
import com.pos.backend.dto.branch.BranchUpdateRequest;
import com.pos.backend.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

  private final BranchService branchService;

  public BranchController(BranchService branchService) {
    this.branchService = branchService;
  }

  @PostMapping
  public ResponseEntity<BranchResponse> create(@Valid @RequestBody BranchCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(branchService.createBranch(req));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BranchResponse> get(@PathVariable Long id) {
    return ResponseEntity.ok(branchService.getBranch(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BranchResponse> update(
    @PathVariable Long id,
    @Valid @RequestBody BranchUpdateRequest req) {
    return ResponseEntity.ok(branchService.updateBranch(id, req));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    branchService.deleteBranch(id);
    return ResponseEntity.noContent().build();
  }
}
