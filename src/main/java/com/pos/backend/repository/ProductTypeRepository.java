package com.pos.backend.repository;

import com.pos.backend.domain.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {}
