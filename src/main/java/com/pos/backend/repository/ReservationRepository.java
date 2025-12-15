package com.pos.backend.repository;

import com.pos.backend.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  List<Reservation> findByMerchant_MerchantIdAndReservationDate(
    Long merchantId,
    LocalDate reservationDate
  );
}
