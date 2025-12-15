package com.pos.backend.dto.reservation;

import com.pos.backend.domain.reservation.ReservationStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationUpdateRequest {

  private Long employeeId;

  @Size(max = 255)
  private String customerName;

  @Size(max = 20)
  private String customerPhone;

  @Min(1)
  private Integer partySize;

  private LocalDate reservationDate;

  private LocalTime startTime;
  private LocalTime endTime;

  private ReservationStatus status;

  public Long getEmployeeId() { return employeeId; }
  public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

  public String getCustomerName() { return customerName; }
  public void setCustomerName(String customerName) { this.customerName = customerName; }

  public String getCustomerPhone() { return customerPhone; }
  public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

  public Integer getPartySize() { return partySize; }
  public void setPartySize(Integer partySize) { this.partySize = partySize; }

  public LocalDate getReservationDate() { return reservationDate; }
  public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

  public LocalTime getStartTime() { return startTime; }
  public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

  public LocalTime getEndTime() { return endTime; }
  public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

  public ReservationStatus getStatus() { return status; }
  public void setStatus(ReservationStatus status) { this.status = status; }
}
