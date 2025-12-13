package com.pos.backend.service;

import com.pos.backend.domain.Branch;
import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.employee.Role;
import com.pos.backend.dto.employee.EmployeeCreateRequest;
import com.pos.backend.dto.employee.EmployeeResponse;
import com.pos.backend.dto.employee.EmployeeUpdateRequest;
import com.pos.backend.repository.BranchRepository;
import com.pos.backend.repository.EmployeeRepository;
import com.pos.backend.repository.MerchantRepository;
import com.pos.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final MerchantRepository merchantRepository;
  private final BranchRepository branchRepository;
  private final RoleRepository roleRepository;

  public EmployeeService(
    EmployeeRepository employeeRepository,
    MerchantRepository merchantRepository,
    BranchRepository branchRepository,
    RoleRepository roleRepository
  ) {
    this.employeeRepository = employeeRepository;
    this.merchantRepository = merchantRepository;
    this.branchRepository = branchRepository;
    this.roleRepository = roleRepository;
  }

  public EmployeeResponse create(EmployeeCreateRequest req) {

    Merchant merchant = merchantRepository.findById(req.getMerchantId())
      .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

    Branch branch = branchRepository.findById(req.getBranchId())
      .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

    Role role = roleRepository.findById(req.getRoleId())
      .orElseThrow(() -> new IllegalArgumentException("Role not found"));

    Employee employee = new Employee(
      merchant,
      branch,
      role,
      req.getEmail(),
      req.getPassword(),
      req.getFirstName(),
      req.getLastName(),
      req.getPhone()
    );

    employeeRepository.save(employee);
    return EmployeeResponse.from(employee);
  }

  @Transactional(readOnly = true)
  public EmployeeResponse get(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

    return EmployeeResponse.from(employee);
  }

  public EmployeeResponse update(Long employeeId, EmployeeUpdateRequest req) {

    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

    if (req.getRoleId() != null) {
      Role role = roleRepository.findById(req.getRoleId())
        .orElseThrow(() -> new IllegalArgumentException("Role not found"));
      employee.setRole(role);
    }

    if (req.getFirstName() != null) {
      employee.setUpdatedAt(java.time.OffsetDateTime.now());
      employee.setStatus(employee.getStatus());
    }

    if (req.getLastName() != null) {
      employee.setUpdatedAt(java.time.OffsetDateTime.now());
    }

    if (req.getPhone() != null) {
      employee.setUpdatedAt(java.time.OffsetDateTime.now());
    }

    if (req.getStatus() != null) {
      employee.setStatus(req.getStatus());
    }

    return EmployeeResponse.from(employee);
  }
}
