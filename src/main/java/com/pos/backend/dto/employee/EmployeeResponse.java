package com.pos.backend.dto.employee;

import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.employee.EmployeeStatus;

import java.time.OffsetDateTime;

public class EmployeeResponse {

  private Long employeeId;
  private Long merchantId;
  private Long branchId;
  private Long roleId;
  private String roleName;

  private String email;
  private String firstName;
  private String lastName;
  private String phone;
  private EmployeeStatus status;

  private OffsetDateTime createdAt;
  private OffsetDateTime lastLogin;

  public static EmployeeResponse from(Employee e) {
    EmployeeResponse r = new EmployeeResponse();

    r.employeeId = e.getEmployeeId();
    r.merchantId = e.getMerchant().getMerchantId();
    r.branchId = e.getBranch().getBranchId();
    r.roleId = e.getRole().getRoleId();
    r.roleName = e.getRole().getName();
    r.email = e.getEmail();
    r.firstName = e.getFirstName();
    r.lastName = e.getLastName();
    r.phone = e.getPhone();
    r.status = e.getStatus();
    r.createdAt = e.getCreatedAt();
    r.lastLogin = e.getLastLogin();

    return r;
  }

  public Long getEmployeeId() { return employeeId; }
  public Long getMerchantId() { return merchantId; }
  public Long getBranchId() { return branchId; }
  public Long getRoleId() { return roleId; }
  public String getRoleName() { return roleName; }
  public String getEmail() { return email; }
  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public String getPhone() { return phone; }
  public EmployeeStatus getStatus() { return status; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getLastLogin() { return lastLogin; }
}
