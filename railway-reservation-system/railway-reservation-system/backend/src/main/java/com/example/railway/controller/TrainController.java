package com.example.railway.controller;

import com.example.railway.entity.Train;
import com.example.railway.service.TrainService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {
    private final TrainService trainService;
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }
    @GetMapping
    public List<Train> list() {
        return trainService.findAll();
    }
    @GetMapping("/search")
    public List<Train> search(@RequestParam String origin,
                              @RequestParam String destination,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return trainService.search(origin, destination, date);
    }
    @PostMapping
    public ResponseEntity<Train> create(@RequestBody Train train) {
        return ResponseEntity.ok(trainService.save(train));
    }
}