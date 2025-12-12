package com.pos.backend.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "branch")
public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long branchId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @Column(nullable = false, length = 255)
  private String name;

  private String addressLine1;
  private String city;
  private String region;
  private String postalCode;

  @Column(nullable = false, length = 20)
  private String phone;

  @Column(nullable = false, length = 255)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 32)
  private BranchStatus status = BranchStatus.active;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  public Branch() {}

  public Branch(Merchant merchant, String name, String addressLine1, String city, String region,
                String postalCode, String phone, String email) {
    this.merchant = merchant;
    this.name = name;
    this.addressLine1 = addressLine1;
    this.city = city;
    this.region = region;
    this.postalCode = postalCode;
    this.phone = phone;
    this.email = email;
  }

  public Long getBranchId() { return branchId; }
  public Merchant getMerchant() { return merchant; }
  public void setMerchant(Merchant merchant) { this.merchant = merchant; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getAddressLine1() { return addressLine1; }
  public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  public String getRegion() { return region; }
  public void setRegion(String region) { this.region = region; }

  public String getPostalCode() { return postalCode; }
  public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public BranchStatus getStatus() { return status; }
  public void setStatus(BranchStatus status) { this.status = status; }

  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
