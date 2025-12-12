package com.pos.backend.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "merchant")
public class Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long merchantId;

  @Column(nullable = false, length = 255)
  private String businessName;

  @Enumerated(EnumType.STRING)
  @Column(name = "business_type", nullable = false, columnDefinition = "business_type_enum")
  private BusinessType businessType;

  private String addressLine1;
  private String city;
  private String region;
  private String postalCode;

  @Column(nullable = false, length = 20)
  private String phone;

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(length = 32)
  private String status = "active";

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Branch> branches = new ArrayList<>();

  public Merchant() {}

  public Merchant(String businessName,
                  BusinessType businessType,
                  String addressLine1,
                  String city,
                  String region,
                  String postalCode,
                  String phone,
                  String email) {
    this.businessName = businessName;
    this.businessType = businessType;
    this.addressLine1 = addressLine1;
    this.city = city;
    this.region = region;
    this.postalCode = postalCode;
    this.phone = phone;
    this.email = email;
  }

  public Long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Long merchantId) {
    this.merchantId = merchantId;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType businessType) {
    this.businessType = businessType;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<Branch> getBranches() {
    return branches;
  }

  public void setBranches(List<Branch> branches) {
    this.branches = branches;
  }
}
