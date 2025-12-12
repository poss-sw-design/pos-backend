package com.pos.backend.dto.merchant;

import com.pos.backend.domain.BusinessType;
import com.pos.backend.domain.Merchant;

public class MerchantResponse {

  private Long merchantId;
  private String businessName;
  private BusinessType businessType;

  private String addressLine1;
  private String city;
  private String region;
  private String postalCode;

  private String phone;
  private String email;

  private String status;

  public static MerchantResponse from(Merchant merchant) {
    MerchantResponse res = new MerchantResponse();
    res.merchantId = merchant.getMerchantId();
    res.businessName = merchant.getBusinessName();
    res.businessType = merchant.getBusinessType();

    res.addressLine1 = merchant.getAddressLine1();
    res.city = merchant.getCity();
    res.region = merchant.getRegion();
    res.postalCode = merchant.getPostalCode();

    res.phone = merchant.getPhone();
    res.email = merchant.getEmail();
    res.status = merchant.getStatus();

    return res;
  }

  public Long getMerchantId() { return merchantId; }
  public String getBusinessName() { return businessName; }
  public BusinessType getBusinessType() { return businessType; }
  public String getAddressLine1() { return addressLine1; }
  public String getCity() { return city; }
  public String getRegion() { return region; }
  public String getPostalCode() { return postalCode; }
  public String getPhone() { return phone; }
  public String getEmail() { return email; }
  public String getStatus() { return status; }
}
