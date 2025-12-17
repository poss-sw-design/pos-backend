package com.pos.backend.controller;

import com.pos.backend.dto.reservation.ReservationCreateRequest;
import com.pos.backend.dto.reservation.ReservationResponse;
import com.pos.backend.dto.reservation.ReservationUpdateRequest;
import com.pos.backend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationResponse create(@RequestBody @Valid ReservationCreateRequest req) {
    return reservationService.createReservation(req);
  }

  @GetMapping
  public List<ReservationResponse> getAllReservations() {
    return reservationService.getAllReservations();
  }

  @GetMapping("/{id}")
  public ReservationResponse getReservation(@PathVariable Long id) {
    return reservationService.getReservationById(id);
  }

  @PutMapping("/{id}")
  public ReservationResponse updateReservation(
    @PathVariable Long id,
    @RequestBody @Valid ReservationUpdateRequest req
  ) {
    return reservationService.updateReservation(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteReservation(@PathVariable Long id) {
    reservationService.deleteReservation(id);
  }

  @PatchMapping("/{id}/cancel")
  public ReservationResponse cancelReservation(@PathVariable Long id) {
    return reservationService.cancelReservation(id);
  }
}
