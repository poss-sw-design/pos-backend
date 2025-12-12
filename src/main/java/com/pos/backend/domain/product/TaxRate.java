package com.pos.backend.domain.product;

import jakarta.persistence.*;

@Entity
@Table(name = "tax_rate")
public class TaxRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taxRateId;

  @Column(nullable = false)
  private Double rate;

  protected TaxRate() {}

  public TaxRate(Double rate) {
    this.rate = rate;
  }

  public Long getTaxRateId() { return taxRateId; }
  public Double getRate() { return rate; }
}
