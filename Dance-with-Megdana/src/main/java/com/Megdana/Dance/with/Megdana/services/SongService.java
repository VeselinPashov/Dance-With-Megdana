package com.Megdana.Dance.with.Megdana.services;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.SongAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.view.SongModelView;
import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SongService(SongRepository songRepository, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
    }

    public void saveNewSong(SongAddForm songAddForm) {
        Song songToAdd = this.modelMapper.map(songAddForm, Song.class);
        this.songRepository.saveAndFlush(songToAdd);
    }

    public List<SongModelView> getAllSongs() {
        return this.songRepository.findAll()
                .stream()
                .map(s -> this.modelMapper.map(s, SongModelView.class))
                .collect(Collectors.toList());
    }

    public void deleteById (Long id) {
        this.songRepository.deleteById(id);
    }

    public void editSong(SongModelView songModelView) {
        Optional<Song> songToEdit = this.songRepository.findById(songModelView.getId());
        if(songToEdit.isPresent()){
            songToEdit.get().setName(songModelView.getName());
            songToEdit.get().setPerformedBy(songModelView.getPerformedBy());
            songToEdit.get().setRegion(songModelView.getRegion());
            songToEdit.get().setMeasure(songModelView.getMeasure());
            this.songRepository.saveAndFlush(songToEdit.get());
        }

    }
}
