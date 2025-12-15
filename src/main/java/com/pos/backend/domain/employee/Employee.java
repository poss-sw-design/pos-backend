package com.pos.backend.domain.employee;

import com.pos.backend.domain.Branch;
import com.pos.backend.domain.Merchant;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long employeeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "branch_id")
  private Branch branch;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(nullable = false, length = 100)
  private String firstName;

  @Column(nullable = false, length = 100)
  private String lastName;

  @Column(length = 20)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private EmployeeStatus status = EmployeeStatus.active;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  private OffsetDateTime lastLogin;

  protected Employee() {}

  public Employee(Branch branch, Role role,
                  String email, String password,
                  String firstName, String lastName, String phone) {
    this.branch = branch;
    this.role = role;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.status = EmployeeStatus.active;
  }

  public Employee(Merchant merchant, Branch branch, Role role, String email, String password, String firstName, String lastName, String phone) {
  }

  public Long getEmployeeId() { return employeeId; }
  public Branch getBranch() { return branch; }
  public void setBranch(Branch branch) { this.branch = branch; }

  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }

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

  public EmployeeStatus getStatus() { return status; }
  public void setStatus(EmployeeStatus status) { this.status = status; }

  public OffsetDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

  public OffsetDateTime getLastLogin() { return lastLogin; }
  public void setLastLogin(OffsetDateTime lastLogin) { this.lastLogin = lastLogin; }

  private Long merchantId;
  public Long getMerchantId() { return merchantId; }
}
