package com.pos.backend.repository;

import com.pos.backend.domain.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
