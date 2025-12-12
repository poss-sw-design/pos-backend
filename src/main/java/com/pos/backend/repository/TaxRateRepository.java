package com.pos.backend.repository;

import com.pos.backend.domain.product.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {}
