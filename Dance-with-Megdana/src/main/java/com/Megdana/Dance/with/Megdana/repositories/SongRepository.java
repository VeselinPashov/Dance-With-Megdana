package com.Megdana.Dance.with.Megdana.repositories;

import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAll();
    Optional<Song> findByName(String name);

    @Override
    Optional<Song> findById(Long aLong);
}
