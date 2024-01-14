package com.Megdana.Dance.with.Megdana.repositories;

import com.Megdana.Dance.with.Megdana.domain.entities.Rehearsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RehearsalRepository extends JpaRepository<Rehearsal, Long> {
    List<Rehearsal> findAll();

    Optional<Rehearsal> findByDate (LocalDate date);
}
