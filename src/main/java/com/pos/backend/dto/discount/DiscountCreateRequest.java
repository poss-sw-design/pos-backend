package com.pos.backend.dto.discount;

import com.pos.backend.domain.discount.DiscountType;
import com.pos.backend.domain.discount.DiscountValueType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DiscountCreateRequest {

  @NotNull
  private String name;

  @NotNull
  private DiscountType type;

  @NotNull
  private DiscountValueType valueType;

  @NotNull
  private BigDecimal value;

  private BigDecimal minimumOrderValue = BigDecimal.ZERO;

  @NotNull
  private OffsetDateTime startTime;

  private OffsetDateTime endTime;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public DiscountType getType() { return type; }
  public void setType(DiscountType type) { this.type = type; }

  public DiscountValueType getValueType() { return valueType; }
  public void setValueType(DiscountValueType valueType) { this.valueType = valueType; }

  public BigDecimal getValue() { return value; }
  public void setValue(BigDecimal value) { this.value = value; }

  public BigDecimal getMinimumOrderValue() { return minimumOrderValue; }
  public void setMinimumOrderValue(BigDecimal minimumOrderValue) { this.minimumOrderValue = minimumOrderValue; }

  public OffsetDateTime getStartTime() { return startTime; }
  public void setStartTime(OffsetDateTime startTime) { this.startTime = startTime; }

  public OffsetDateTime getEndTime() { return endTime; }
  public void setEndTime(OffsetDateTime endTime) { this.endTime = endTime; }
}
