package com.example.skillfactory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Дата начала бронирования обязательна")
    private LocalDate startDate;

    @NotNull(message = "Дата окончания бронирования обязательна")
    private LocalDate endDate;

    @NotNull(message = "Сумма бронирования обязательна")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "status", length = 50)
    private String status;

    private boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "Дата начала бронирования обязательна") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = "Дата начала бронирования обязательна") LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull(message = "Дата окончания бронирования обязательна") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = "Дата окончания бронирования обязательна") LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotNull(message = "Сумма бронирования обязательна") BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull(message = "Сумма бронирования обязательна") BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
