package com.Megdana.Dance.with.Megdana.domain.dto.view;

import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;

import java.time.LocalDate;
import java.util.Date;

public class DanceModelView {

    private Long id;

    private String name;

    private Song song;

    private Region region;

    private Measure measure;

    private LocalDate learnedDate;

    public DanceModelView() {
    }

    public Long getId() {
        return id;
    }

    public DanceModelView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DanceModelView setName(String name) {
        this.name = name;
        return this;
    }

    public Song getSong() {
        return song;
    }

    public DanceModelView setSong(Song song) {
        this.song = song;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public DanceModelView setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public DanceModelView setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public LocalDate getLearnedDate() {
        return learnedDate;
    }

    public DanceModelView setLearnedDate(LocalDate learnedDate) {
        this.learnedDate = learnedDate;
        return this;
    }
}
