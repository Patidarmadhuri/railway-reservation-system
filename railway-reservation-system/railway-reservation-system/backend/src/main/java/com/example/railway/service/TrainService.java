package com.example.railway.service;

import com.example.railway.entity.Train;
import com.example.railway.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainService {
    private final TrainRepository trainRepository;
    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> search(String origin, String destination, LocalDate date) {
        // Search trains on a particular day between midnight and the end of the day
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);
        return trainRepository.findByOriginIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeBetween(
                origin, destination, start, end);
    }

    public List<Train> findAll() {
        return trainRepository.findAll();
    }

    public Train save(Train train) {
        return trainRepository.save(train);
    }
}