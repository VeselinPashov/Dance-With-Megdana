package com.Megdana.Dance.with.Megdana.repositories;

import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DanceRepository extends JpaRepository<Dance, Long> {

    Optional<Dance> findByName (String name);
}
