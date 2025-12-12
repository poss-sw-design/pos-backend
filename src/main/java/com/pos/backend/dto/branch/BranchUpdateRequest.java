package com.pos.backend.dto.branch;

public class BranchUpdateRequest {

  private String name;
  private String addressLine1;
  private String city;
  private String region;
  private String postalCode;
  private String phone;
  private String email;
  private String status;

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

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}
