package com.pos.backend.domain.product;

import jakarta.persistence.*;

@Entity
@Table(name = "product_type")
public class ProductType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productTypeId;

  @Column(nullable = false, length = 255)
  private String productTypeName;

  protected ProductType() {}

  public ProductType(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public Long getProductTypeId() { return productTypeId; }
  public String getProductTypeName() { return productTypeName; }
}
