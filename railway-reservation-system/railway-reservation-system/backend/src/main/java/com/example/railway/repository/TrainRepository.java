package com.example.railway.repository;

import com.example.railway.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findByOriginIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeBetween(
            String origin, String destination, LocalDateTime from, LocalDateTime to);
}