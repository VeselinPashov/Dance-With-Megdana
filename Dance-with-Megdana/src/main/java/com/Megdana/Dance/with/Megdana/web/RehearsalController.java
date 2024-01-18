package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.RehearsalAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.RehearsalModel;
import com.Megdana.Dance.with.Megdana.domain.entities.Rehearsal;
import com.Megdana.Dance.with.Megdana.repositories.DanceRepository;
import com.Megdana.Dance.with.Megdana.repositories.RehearsalRepository;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import com.Megdana.Dance.with.Megdana.services.RehearsalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("rehearsals")
public class RehearsalController extends BaseController{
    private final RehearsalRepository rehearsalRepository;
    private final RehearsalService rehearsalService;
    private final DanceRepository danceRepository;
    private final ModelMapper modelMapper;

    public RehearsalController(RehearsalRepository rehearsalRepository, SongRepository songRepository, RehearsalService rehearsalService, DanceRepository danceRepository, ModelMapper modelMapper) {
        this.rehearsalRepository = rehearsalRepository;
        this.rehearsalService = rehearsalService;
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
    public ModelAndView postAddRehearsal (@Validated RehearsalAddForm rehearsalAddForm,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        if (this.rehearsalRepository.findByDate(rehearsalAddForm.getDate()).isPresent()) {
            bindingResult.rejectValue("Date", null, "There is already another rehearsal started on the same date");
        }

        if(bindingResult.hasErrors()) {
            modelAndView.addObject("rehearsalList", this.rehearsalRepository.findAll());
            modelAndView.addObject(rehearsalAddForm);
            return super.view("addRehearsal", modelAndView);
        }

        this.rehearsalService.saveNewRehearsal(rehearsalAddForm);
        return redirect("/rehearsals/");
    }

    @RequestMapping("/deleteRehearsal/{id}")
    public ModelAndView getDeleteRehearsal (@PathVariable Long id,
                                        ModelAndView modelAndView) {
        this.rehearsalRepository.deleteById(id);
        return redirect("/rehearsals/");
    }

    @RequestMapping("/editRehearsal/{id}")
    public ModelAndView getEditRehearsal (@PathVariable Long id,
                                          ModelAndView modelAndView) {
        Optional<Rehearsal> rehearsalToEdit = this.rehearsalRepository.findById(id);
        rehearsalToEdit.ifPresent(rehearsal -> modelAndView.addObject("rehearsalToEdit", rehearsal));
        return super.view("editRehearsal", modelAndView);
    }

    @PostMapping("/editRehearsal")
    public ModelAndView postEditRehearsal (@ModelAttribute RehearsalModel rehearsalModel,
                                           ModelAndView modelAndView) {
        this.rehearsalService.editRehearsal(rehearsalModel);
        return redirect("/rehearsals/");
    }

    @ModelAttribute ("rehearsalAddForm")
    public RehearsalAddForm rehearsalAddForm() {
        return new RehearsalAddForm();
    }
}
