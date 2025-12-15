package com.pos.backend.controller;

import com.pos.backend.dto.branch.BranchResponse;
import com.pos.backend.dto.employee.EmployeeCreateRequest;
import com.pos.backend.dto.employee.EmployeeResponse;
import com.pos.backend.dto.employee.EmployeeUpdateRequest;
import com.pos.backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeeResponse create(@RequestBody @Valid EmployeeCreateRequest req) {
    return employeeService.create(req);
  }

  @GetMapping("/{employeeId}")
  public EmployeeResponse get(@PathVariable Long employeeId) {
    return employeeService.get(employeeId);
  }

  @PatchMapping("/{employeeId}")
  public EmployeeResponse update(
    @PathVariable Long employeeId,
    @RequestBody EmployeeUpdateRequest req
  ) {
    return employeeService.update(employeeId, req);
  }

  @GetMapping
  public List<EmployeeResponse> getAllEmployees() {
    return employeeService.getAllEmployees();
  }
}
