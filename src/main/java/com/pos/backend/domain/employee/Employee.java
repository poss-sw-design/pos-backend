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
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "branch_id", nullable = false)
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
  private EmployeeStatus status = EmployeeStatus.ACTIVE;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  private OffsetDateTime lastLogin;

  protected Employee() {}

  public Employee(Merchant merchant, Branch branch, Role role,
                  String email, String password,
                  String firstName, String lastName, String phone) {

    this.merchant = merchant;
    this.branch = branch;
    this.role = role;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
  }

  public Long getEmployeeId() { return employeeId; }
  public Merchant getMerchant() { return merchant; }
  public Branch getBranch() { return branch; }
  public Role getRole() { return role; }
  public String getEmail() { return email; }
  public String getPassword() { return password; }
  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }
  public String getPhone() { return phone; }
  public EmployeeStatus getStatus() { return status; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public OffsetDateTime getLastLogin() { return lastLogin; }

  public void setRole(Role role) { this.role = role; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setPhone(String phone) { this.phone = phone; }
  public void setStatus(EmployeeStatus status) { this.status = status; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
  public void setLastLogin(OffsetDateTime lastLogin) { this.lastLogin = lastLogin; }
}
