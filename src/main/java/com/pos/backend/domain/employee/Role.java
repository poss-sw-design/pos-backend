package com.pos.backend.domain.employee;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roleId;

  @Column(nullable = false, unique = true, length = 50)
  private String name;

  @Column(length = 255)
  private String description;

  @ManyToMany
  @JoinTable(
    name = "role_permission",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id")
  )
  private Set<Permission> permissions = new HashSet<>();

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  protected Role() {}

  public Role(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Long getRoleId() { return roleId; }
  public String getName() { return name; }
  public String getDescription() { return description; }
  public Set<Permission> getPermissions() { return permissions; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
}
