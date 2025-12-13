package com.pos.backend.domain.discount;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "discount")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  private Double value;

  @Column(nullable = false, precision = 10, scale = 2)
  private Double minimumOrderValue = 0.0;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private DiscountStatus status = DiscountStatus.INACTIVE;

  private Integer currentUses = 0;
  private Integer maxUses;

  @Column(nullable = false)
  private OffsetDateTime startTime;

  private OffsetDateTime endTime;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  protected Discount() {}

  /* getters / setters */

  public Long getDiscountId() { return discountId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public DiscountType getType() { return type; }
  public void setType(DiscountType type) { this.type = type; }

  public DiscountValueType getValueType() { return valueType; }
  public void setValueType(DiscountValueType valueType) { this.valueType = valueType; }

  public Double getValue() { return value; }
  public void setValue(Double value) { this.value = value; }

  public Double getMinimumOrderValue() { return minimumOrderValue; }

  public DiscountStatus getStatus() { return status; }
  public void setStatus(DiscountStatus status) { this.status = status; }

  public OffsetDateTime getStartTime() { return startTime; }
  public void setStartTime(OffsetDateTime startTime) { this.startTime = startTime; }

  public OffsetDateTime getEndTime() { return endTime; }
}
