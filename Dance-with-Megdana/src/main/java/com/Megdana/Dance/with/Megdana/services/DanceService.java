package com.Megdana.Dance.with.Megdana.services;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.DanceAddForm;
import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import com.Megdana.Dance.with.Megdana.repositories.DanceRepository;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DanceService {

    private final DanceRepository danceRepository;

    private  final SongRepository songRepository;
    private final ModelMapper modelMapper;

    public DanceService(DanceRepository danceRepository, SongRepository songRepository, ModelMapper modelMapper) {
        this.danceRepository = danceRepository;
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
    }

    public void saveNewDance(DanceAddForm danceAddForm) {
        danceAddForm.setLearnedDate(LocalDate.now());
        this.danceRepository.saveAndFlush(this.modelMapper.map(danceAddForm, Dance.class));
    }

    public ModelAndView listAllDances (ModelAndView modelAndView) {
        modelAndView.addObject("songList", this.songRepository.findAll());
        modelAndView.addObject("regions", Arrays.asList(Region.values()));
        modelAndView.addObject("measures", Arrays.asList(Measure.values()));
        return  modelAndView;
    }
}
