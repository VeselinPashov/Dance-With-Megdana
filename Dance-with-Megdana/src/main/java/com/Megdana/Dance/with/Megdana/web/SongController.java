package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.SongAddForm;
import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import com.Megdana.Dance.with.Megdana.services.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/songs")
public class SongController extends BaseController{
    private final SongService songService;

    public SongController(SongRepository songRepository, SongService songService, ModelMapper modelMapper) {
        this.songService = songService;
    }

    @RequestMapping ("/all")
    public ModelAndView getAllSongs (ModelAndView modelAndView) {
        modelAndView.addObject("songList", this.songService.getAllSongs());
        return super.view("songs", modelAndView);
    }

    @RequestMapping("/add")
    public ModelAndView getAddSong (ModelAndView modelAndView) {
        modelAndView.addObject("songAddForm", new SongAddForm());
        modelAndView.addObject("regions", Arrays.asList(Region.values()));
        modelAndView.addObject("measures", Arrays.asList(Measure.values()));
        return super.view("addSong", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView postAddSong (@ModelAttribute SongAddForm songAddForm,
                                     ModelAndView modelAndView) {
        this.songService.saveNewSong(songAddForm);
        return redirect("/songs/all");
    }

    @ModelAttribute("songAddForm")
    public SongAddForm initSongAddForm() {
        return new SongAddForm();
    }
}
