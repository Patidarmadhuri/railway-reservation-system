package com.example.railway.service;

import com.example.railway.entity.Reservation;
import com.example.railway.entity.Train;
import com.example.railway.repository.ReservationRepository;
import com.example.railway.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final TrainRepository trainRepository;
    public ReservationService(ReservationRepository reservationRepository, TrainRepository trainRepository) {
        this.reservationRepository = reservationRepository;
        this.trainRepository = trainRepository;
    }

    public List<Reservation> listByPassenger(String email) {
        return reservationRepository.findByPassengerEmail(email);
    }

    @Transactional
    public Reservation book(Long trainId, String passengerName, String passengerEmail, int seats) {
        Train train = trainRepository.findById(trainId).orElseThrow();
        if (train.getAvailableSeats() < seats) {
            throw new IllegalStateException("Not enough seats available");
        }
        train.setAvailableSeats(train.getAvailableSeats() - seats);
        trainRepository.save(train);
        Reservation reservation = new Reservation();
        reservation.setTrain(train);
        reservation.setPassengerName(passengerName);
        reservation.setPassengerEmail(passengerEmail);
        reservation.setSeatsBooked(seats);
        return reservationRepository.save(reservation);
    }
}