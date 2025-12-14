package com.pos.backend.repository;

import com.pos.backend.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
  Optional<Merchant> findByEmail(String email);
}
