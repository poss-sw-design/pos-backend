package com.pos.backend.domain.product;

import com.pos.backend.domain.Merchant;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  private Integer price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_type_id")
  private ProductType productType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tax_rate_id")
  private TaxRate taxRate;

  @Column(columnDefinition = "TEXT")
  private String description;

  private String imageUrl;

  @Column(nullable = false, length = 32)
  @Enumerated(EnumType.STRING)
  private ProductStatus status = ProductStatus.ACTIVE;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  protected Product() {}

  public Product(Merchant merchant, String name, Integer price,
                 ProductType productType, TaxRate taxRate,
                 String description, String imageUrl) {
    this.merchant = merchant;
    this.name = name;
    this.price = price;
    this.productType = productType;
    this.taxRate = taxRate;
    this.description = description;
    this.imageUrl = imageUrl;
    this.status = ProductStatus.ACTIVE;
  }

  public Long getProductId() { return productId; }
  public Merchant getMerchant() { return merchant; }
  public void setMerchant(Merchant merchant) { this.merchant = merchant; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Integer getPrice() { return price; }
  public void setPrice(Integer price) { this.price = price; }

  public ProductType getProductType() { return productType; }
  public void setProductType(ProductType productType) { this.productType = productType; }

  public TaxRate getTaxRate() { return taxRate; }
  public void setTaxRate(TaxRate taxRate) { this.taxRate = taxRate; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

  public ProductStatus getStatus() { return status; }
  public void setStatus(ProductStatus status) { this.status = status; }

  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
