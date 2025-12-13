package com.pos.backend.domain.product;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_rate")
public class TaxRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taxRateId;

  @Column(nullable = false, precision = 5, scale = 2)
  private BigDecimal rate;

  protected TaxRate() {}

  public TaxRate(BigDecimal rate) {
    this.rate = rate;
  }

  public Long getTaxRateId() { return taxRateId; }
  public BigDecimal getRate() { return rate; }
}
