package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.RehearsalAddForm;
import com.Megdana.Dance.with.Megdana.domain.entities.Rehearsal;
import com.Megdana.Dance.with.Megdana.repositories.DanceRepository;
import com.Megdana.Dance.with.Megdana.repositories.RehearsalRepository;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rehearsals")
public class RehearsalController extends BaseController{
    private final RehearsalRepository rehearsalRepository;
    private final DanceRepository danceRepository;
    private final ModelMapper modelMapper;

    public RehearsalController(RehearsalRepository rehearsalRepository, SongRepository songRepository, DanceRepository danceRepository, ModelMapper modelMapper) {
        this.rehearsalRepository = rehearsalRepository;
        this.danceRepository = danceRepository;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/")
    public ModelAndView getRehearsalHomePage (ModelAndView modelAndView) {
        modelAndView.addObject("rehearsalList", this.rehearsalRepository.findAll());
        return super.view("rehearsals", modelAndView);
    }

    @RequestMapping("/add")
    public ModelAndView getAddRehearsal (ModelAndView modelAndView){
        modelAndView.addObject("danceList", this.danceRepository.findAll());
        return super.view("addRehearsal", modelAndView);
    }

    @PostMapping ("/add")
    public ModelAndView postAddRehearsal (@ModelAttribute RehearsalAddForm rehearsalAddForm,
                                            ModelAndView modelAndView) {

        this.rehearsalRepository.saveAndFlush(this.modelMapper.map(rehearsalAddForm, Rehearsal.class));
        return redirect("/rehearsals/");
    }

    @RequestMapping("/deleteRehearsal/{id}")
    public ModelAndView getDeleteRehearsal (@PathVariable Long id,
                                        ModelAndView modelAndView) {
        this.rehearsalRepository.deleteById(id);
        return redirect("/rehearsals/");
    }

    @ModelAttribute ("rehearsalAddForm")
    public RehearsalAddForm rehearsalAddForm() {
        return new RehearsalAddForm();
    }
}
