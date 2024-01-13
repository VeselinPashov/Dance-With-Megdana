package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.DanceAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.view.DanceModelView;
import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import com.Megdana.Dance.with.Megdana.repositories.DanceRepository;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import com.Megdana.Dance.with.Megdana.services.DanceService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/dances")
public class DanceController extends BaseController{
    private final DanceRepository danceRepository;
    private final DanceService danceService;

    public DanceController(DanceRepository danceRepository, DanceService danceService, SongRepository songRepository) {
        this.danceRepository = danceRepository;
        this.danceService = danceService;
    }

    @RequestMapping("/all")
    public ModelAndView getAllDances(ModelAndView modelAndView) {
        modelAndView.addObject("danceList", this.danceRepository.findAll());
        return super.view("dances", modelAndView);
    }

    @RequestMapping("/add")
    public ModelAndView getAddDance (ModelAndView modelAndView) {
        return super.view("addDance", this.danceService.listAllSongsAndRegions(modelAndView));
    }

    @PostMapping("/add")
    public ModelAndView postAddDance (@Validated DanceAddForm danceAddForm,
                                      BindingResult bindingResult,
                                      ModelAndView modelAndView) {
        if (this.danceRepository.findByName(danceAddForm.getName()).isPresent()) {
            bindingResult.rejectValue("name", null, "There is already a dance saved with the same name");
        }

        if(bindingResult.hasErrors()) {
            return super.view("addDance", this.danceService.listAllSongsAndRegions(modelAndView.addObject(danceAddForm)));
        }
        this.danceService.saveNewDance(danceAddForm);
        return redirect("/dances/all");
    }

    @RequestMapping("/deleteDance/{id}")
    public ModelAndView getDeleteDance (@PathVariable Long id,
                                         ModelAndView modelAndView) {
        this.danceRepository.deleteById(id);
        return redirect("/dances/all");
    }

    @RequestMapping("/editDance/{id}")
    public ModelAndView getEditDance (@PathVariable Long id,
                                      ModelAndView modelAndView) {
        Optional<Dance> danceToEdit = this.danceRepository.findById(id);
        danceToEdit.ifPresent(dance -> modelAndView.addObject("danceToEdit", dance));

        return super.view("editDance", this.danceService.listAllSongsAndRegions(modelAndView));
    }

    @PostMapping("/editDance")
    public ModelAndView postEditDance (@ModelAttribute DanceModelView danceModelView,
                                      ModelAndView modelAndView) {
        this.danceService.editDance(danceModelView);
        return redirect("/dances/all");
    }


    @ModelAttribute("danceAddForm")
    public DanceAddForm initDanceAddForm() {
        return new DanceAddForm();
    }

}
