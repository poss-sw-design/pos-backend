package com.pos.backend.dto.reservation;

import com.pos.backend.domain.reservation.Reservation;
import com.pos.backend.domain.reservation.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public class ReservationResponse {

  private Long reservationId;

  private Long merchantId;
  private Long employeeId;

  private String customerName;
  private String customerPhone;

  private Integer partySize;

  private LocalDate reservationDate;
  private LocalTime startTime;
  private LocalTime endTime;

  private ReservationStatus status;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public static ReservationResponse from(Reservation r) {
    ReservationResponse res = new ReservationResponse();

    res.reservationId = r.getReservationId();
    res.merchantId = r.getMerchant().getMerchantId();
    res.employeeId = r.getAssignedEmployee() != null ? r.getAssignedEmployee().getEmployeeId() : null;

    res.customerName = r.getCustomerName();
    res.customerPhone = r.getCustomerPhone();
    res.partySize = r.getPartySize();

    res.reservationDate = r.getReservationDate();
    res.startTime = r.getStartTime();
    res.endTime = r.getEndTime();

    res.status = r.getStatus();
    res.createdAt = r.getCreatedAt();
    res.updatedAt = r.getUpdatedAt();

    return res;
  }

  public Long getReservationId() {
    return reservationId;
  }

  public Long getMerchantId() {
    return merchantId;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getCustomerPhone() {
    return customerPhone;
  }

  public Integer getPartySize() {
    return partySize;
  }

  public LocalDate getReservationDate() {
    return reservationDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }
}
