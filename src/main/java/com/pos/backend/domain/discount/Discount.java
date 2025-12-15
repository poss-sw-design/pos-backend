package com.pos.backend.domain.discount;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "discount")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "discount_id")
  private Long discountId;

  @Column(nullable = false, length = 255)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private DiscountType type;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private DiscountValueType valueType;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal value;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal minimumOrderValue = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private DiscountStatus status;

  private Integer currentUses = 0;
  private Integer maxUses;

  @Column(nullable = false)
  private OffsetDateTime startTime;

  private OffsetDateTime endTime;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  public Discount() {}

  public Long getDiscountId() { return discountId; }

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

  public DiscountStatus getStatus() { return status; }
  public void setStatus(DiscountStatus status) { this.status = status; }

  public Integer getCurrentUses() { return currentUses; }
  public void setCurrentUses(Integer currentUses) { this.currentUses = currentUses; }

  public Integer getMaxUses() { return maxUses; }
  public void setMaxUses(Integer maxUses) { this.maxUses = maxUses; }

  public OffsetDateTime getStartTime() { return startTime; }
  public void setStartTime(OffsetDateTime startTime) { this.startTime = startTime; }

  public OffsetDateTime getEndTime() { return endTime; }
  public void setEndTime(OffsetDateTime endTime) { this.endTime = endTime; }

  public OffsetDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
