package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.SongAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.view.SongModelView;
import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import com.Megdana.Dance.with.Megdana.services.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/songs")
public class SongController extends BaseController{
    private final SongService songService;
    private final SongRepository songRepository;

    public SongController(SongRepository songRepository, SongService songService, ModelMapper modelMapper, SongRepository songRepository1) {
        this.songService = songService;
        this.songRepository = songRepository1;
    }

    @RequestMapping ("/all")
    public ModelAndView getAllSongs (ModelAndView modelAndView) {
        List<SongModelView> allSongs = this.songService.getAllSongs()
                .stream()
                .sorted(Comparator.comparing(SongModelView::getName))
                .collect(Collectors.toList());;
        modelAndView.addObject("songList", allSongs);
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
    public ModelAndView postAddSong (@Validated SongAddForm songAddForm,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        if (this.songRepository.findByName(songAddForm.getName()).isPresent()) {
            bindingResult.rejectValue("name", null, "There is already a song saved with the same name");
        }

        if (bindingResult.hasErrors()) {
            return super.view("addSong", modelAndView.addObject(songAddForm));
        }

        this.songService.saveNewSong(songAddForm);
        return redirect("/songs/all");
    }

    @RequestMapping("/deleteSong/{id}")
    public ModelAndView getDeleteSong(@PathVariable Long id,
                                      ModelAndView modelAndView) {
        this.songService.deleteById(id);
        return redirect("/songs/all");
    }

    @RequestMapping("/editSong/{id}")
    public ModelAndView getEditSong (@PathVariable Long id,
                                     ModelAndView modelAndView) {
        Optional<Song> songToEdit = this.songRepository.findById(id);
        songToEdit.ifPresent(song -> modelAndView.addObject("songToEdit", song));
        modelAndView.addObject("regions", Arrays.asList(Region.values()));
        modelAndView.addObject("measures", Arrays.asList(Measure.values()));
        return super.view("editSong", modelAndView);
    }

    @PostMapping("/editSong")
    public ModelAndView postEditSong (@ModelAttribute SongModelView songModelView,
                                      ModelAndView modelAndView) {
        this.songService.editSong(songModelView);
        return redirect("/songs/all");
    }


    @ModelAttribute("songAddForm")
    public SongAddForm initSongAddForm() {
        return new SongAddForm();
    }
}
