package com.pos.backend.dto.discount;

import com.pos.backend.domain.discount.Discount;
import com.pos.backend.domain.discount.DiscountStatus;
import com.pos.backend.domain.discount.DiscountType;
import com.pos.backend.domain.discount.DiscountValueType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DiscountResponse {

  private Long discountId;
  private String name;
  private DiscountType type;
  private DiscountValueType valueType;
  private BigDecimal value;
  private BigDecimal minimumOrderValue;
  private DiscountStatus status;
  private OffsetDateTime startTime;
  private OffsetDateTime endTime;

  public static DiscountResponse from(Discount discount) {
    DiscountResponse r = new DiscountResponse();
    r.discountId = discount.getDiscountId();
    r.name = discount.getName();
    r.type = discount.getType();
    r.valueType = discount.getValueType();
    r.value = discount.getValue();
    r.minimumOrderValue = discount.getMinimumOrderValue();
    r.status = discount.getStatus();
    r.startTime = discount.getStartTime();
    r.endTime = discount.getEndTime();
    return r;
  }

  public Long getDiscountId() { return discountId; }
  public String getName() { return name; }
  public DiscountType getType() { return type; }
  public DiscountValueType getValueType() { return valueType; }
  public BigDecimal getValue() { return value; }
  public BigDecimal getMinimumOrderValue() { return minimumOrderValue; }
  public DiscountStatus getStatus() { return status; }
  public OffsetDateTime getStartTime() { return startTime; }
  public OffsetDateTime getEndTime() { return endTime; }
}
