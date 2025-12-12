package com.pos.backend.dto.branch;

import com.pos.backend.domain.Branch;

public class BranchResponse {

  private Long branchId;
  private Long merchantId;
  private String name;
  private String addressLine1;
  private String city;
  private String region;
  private String postalCode;
  private String phone;
  private String email;
  private String status;

  public static BranchResponse from(Branch b) {
    BranchResponse res = new BranchResponse();
    res.branchId = b.getBranchId();
    res.merchantId = b.getMerchant().getMerchantId();
    res.name = b.getName();
    res.addressLine1 = b.getAddressLine1();
    res.city = b.getCity();
    res.region = b.getRegion();
    res.postalCode = b.getPostalCode();
    res.phone = b.getPhone();
    res.email = b.getEmail();
    res.status = b.getStatus();
    return res;
  }

  public Long getBranchId() { return branchId; }
  public Long getMerchantId() { return merchantId; }
  public String getName() { return name; }
  public String getAddressLine1() { return addressLine1; }
  public String getCity() { return city; }
  public String getRegion() { return region; }
  public String getPostalCode() { return postalCode; }
  public String getPhone() { return phone; }
  public String getEmail() { return email; }
  public String getStatus() { return status; }
}
