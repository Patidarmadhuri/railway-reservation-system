package com.example.railway.controller;

import com.example.railway.entity.Reservation;
import com.example.railway.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public List<Reservation> list(@RequestParam String email) {
        return reservationService.listByPassenger(email);
    }
    /**
     * Book seats on a train.
     */
    @PostMapping("/book")
    public ResponseEntity<Reservation> book(@RequestParam Long trainId,
                                            @RequestParam String name,
                                            @RequestParam String email,
                                            @RequestParam int seats) {
        return ResponseEntity.ok(reservationService.book(trainId, name, email, seats));
    }
}