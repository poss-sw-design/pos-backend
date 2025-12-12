package com.pos.backend.dto.branch;

import com.pos.backend.domain.Branch;
import com.pos.backend.domain.BranchStatus;

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

  private BranchStatus status;

  public static BranchResponse from(Branch branch) {
    BranchResponse res = new BranchResponse();

    res.branchId = branch.getBranchId();
    res.merchantId = branch.getMerchant().getMerchantId();
    res.name = branch.getName();

    res.addressLine1 = branch.getAddressLine1();
    res.city = branch.getCity();
    res.region = branch.getRegion();
    res.postalCode = branch.getPostalCode();

    res.phone = branch.getPhone();
    res.email = branch.getEmail();
    res.status = branch.getStatus();

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
  public BranchStatus getStatus() { return status; }
}
