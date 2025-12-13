package com.pos.backend.domain.employee;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(
  name = "permission",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"resource", "action"})
  }
)
public class Permission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long permissionId;

  @Column(nullable = false, unique = true, length = 100)
  private String name;

  @Column(nullable = false, length = 50)
  private String resource;

  @Column(nullable = false, length = 50)
  private String action;

  @Column(length = 255)
  private String description;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  protected Permission() {}

  public Permission(String name, String resource, String action, String description) {
    this.name = name;
    this.resource = resource;
    this.action = action;
    this.description = description;
  }

  public Long getPermissionId() { return permissionId; }
  public String getName() { return name; }
  public String getResource() { return resource; }
  public String getAction() { return action; }
  public String getDescription() { return description; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
}
