package com.pos.backend.dto.employee;

import jakarta.validation.constraints.*;

public class EmployeeCreateRequest {

  @NotNull
  private Long merchantId;

  @NotNull
  private Long branchId;

  @NotNull
  private Long roleId;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  private String phone;

  public Long getMerchantId() { return merchantId; }
  public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }

  public Long getBranchId() { return branchId; }
  public void setBranchId(Long branchId) { this.branchId = branchId; }

  public Long getRoleId() { return roleId; }
  public void setRoleId(Long roleId) { this.roleId = roleId; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
}
