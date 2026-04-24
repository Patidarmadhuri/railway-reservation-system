package com.example.railway.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a booking made by a passenger.
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passengerName;
    private String passengerEmail;
    private int seatsBooked;
    private LocalDateTime bookingTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    public Reservation() {
        this.bookingTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public String getPassengerEmail() { return passengerEmail; }
    public void setPassengerEmail(String passengerEmail) { this.passengerEmail = passengerEmail; }
    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
}