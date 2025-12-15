package com.pos.backend.domain.reservation;

import com.pos.backend.domain.Merchant;
import com.pos.backend.domain.employee.Employee;
import com.pos.backend.domain.order.Order;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private Employee assignedEmployee;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(nullable = false, length = 100)
  private String customerName;

  @Column(nullable = false, length = 20)
  private String customerPhone;

  @Column(nullable = false)
  private Integer partySize;

  @Column(nullable = false)
  private LocalDate reservationDate;

  @Column(nullable = false)
  private LocalTime startTime;

  private LocalTime endTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private ReservationStatus status = ReservationStatus.pending;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(nullable = false)
  private OffsetDateTime updatedAt = OffsetDateTime.now();

  protected Reservation() {}

  public Reservation(
    Merchant merchant,
    Employee assignedEmployee,
    String customerName,
    String customerPhone,
    Integer partySize,
    LocalDate reservationDate,
    LocalTime startTime,
    LocalTime endTime
  ) {
    this.merchant = merchant;
    this.assignedEmployee = assignedEmployee;
    this.customerName = customerName;
    this.customerPhone = customerPhone;
    this.partySize = partySize;
    this.reservationDate = reservationDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Long getReservationId() { return reservationId; }
  public Merchant getMerchant() { return merchant; }
  public Employee getAssignedEmployee() { return assignedEmployee; }
  public void setAssignedEmployee(Employee assignedEmployee) { this.assignedEmployee = assignedEmployee; }
  public Order getOrder() { return order; }
  public void setOrder(Order order) { this.order = order; }
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
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = OffsetDateTime.now();
  }
}
