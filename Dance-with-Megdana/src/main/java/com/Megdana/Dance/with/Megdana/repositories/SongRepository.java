package com.Megdana.Dance.with.Megdana.repositories;

import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAll();
}
