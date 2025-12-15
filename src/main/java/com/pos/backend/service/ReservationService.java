package com.pos.backend.service;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.reservation.Reservation;
import com.pos.backend.domain.reservation.ReservationStatus;
import com.pos.backend.dto.reservation.ReservationCreateRequest;
import com.pos.backend.dto.reservation.ReservationResponse;
import com.pos.backend.dto.reservation.ReservationUpdateRequest;
import com.pos.backend.repository.EmployeeRepository;
import com.pos.backend.repository.MerchantRepository;
import com.pos.backend.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final MerchantRepository merchantRepository;
  private final EmployeeRepository employeeRepository;

  public ReservationService(
    ReservationRepository reservationRepository,
    MerchantRepository merchantRepository,
    EmployeeRepository employeeRepository
  ) {
    this.reservationRepository = reservationRepository;
    this.merchantRepository = merchantRepository;
    this.employeeRepository = employeeRepository;
  }

  @Transactional
  public ReservationResponse createReservation(ReservationCreateRequest req) {

    Merchant merchant = merchantRepository.findById(req.getMerchantId())
      .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

    Employee employee = null;
    if (req.getEmployeeId() != null) {
      employee = employeeRepository.findById(req.getEmployeeId())
        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    Reservation reservation = new Reservation(
      merchant,
      employee,
      req.getCustomerName(),
      req.getCustomerPhone(),
      req.getPartySize(),
      req.getReservationDate(),
      req.getStartTime(),
      req.getEndTime()
    );

    reservationRepository.save(reservation);
    return ReservationResponse.from(reservation);
  }

  @Transactional(readOnly = true)
  public List<ReservationResponse> getAllReservations() {
    return reservationRepository.findAll()
      .stream()
      .map(ReservationResponse::from)
      .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public ReservationResponse getReservationById(Long id) {
    Reservation reservation = reservationRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    return ReservationResponse.from(reservation);
  }

  @Transactional
  public ReservationResponse updateReservation(Long id, ReservationUpdateRequest req) {
    Reservation reservation = reservationRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

    if (req.getEmployeeId() != null) {
      Employee employee = employeeRepository.findById(req.getEmployeeId())
        .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
      reservation.setAssignedEmployee(employee);
    }

    if (req.getCustomerName() != null) reservation.setCustomerName(req.getCustomerName());
    if (req.getCustomerPhone() != null) reservation.setCustomerPhone(req.getCustomerPhone());
    if (req.getPartySize() != null) reservation.setPartySize(req.getPartySize());
    if (req.getReservationDate() != null) reservation.setReservationDate(req.getReservationDate());
    if (req.getStartTime() != null) reservation.setStartTime(req.getStartTime());
    if (req.getEndTime() != null) reservation.setEndTime(req.getEndTime());
    if (req.getStatus() != null) reservation.setStatus(ReservationStatus.valueOf(String.valueOf(req.getStatus())));

    reservationRepository.save(reservation);
    return ReservationResponse.from(reservation);
  }

  @Transactional
  public void deleteReservation(Long id) {
    Reservation reservation = reservationRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    reservationRepository.delete(reservation);
  }
}
