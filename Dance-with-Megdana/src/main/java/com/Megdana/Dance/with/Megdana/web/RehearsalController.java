package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.RehearsalAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.RehearsalModel;
import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import com.Megdana.Dance.with.Megdana.domain.entities.Rehearsal;
import com.Megdana.Dance.with.Megdana.repositories.DanceRepository;
import com.Megdana.Dance.with.Megdana.repositories.RehearsalRepository;
import com.Megdana.Dance.with.Megdana.repositories.SongRepository;
import com.Megdana.Dance.with.Megdana.services.RehearsalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
            modelAndView.addObject("danceList", this.danceRepository.findAll());
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

    @GetMapping("/rehearsalDetails/{id}")
    public ModelAndView getRehearsalDetails (@PathVariable Long id,
                                             ModelAndView modelAndView) {
        Optional<Rehearsal> rehearsalToShow = this.rehearsalRepository.findById(id);

        rehearsalToShow.ifPresent(rehearsal -> {modelAndView.addObject("rehearsalDetails", rehearsal);
                                                modelAndView.addObject("danceList", rehearsal.getDances());}
                                                );
        modelAndView.addObject("allDanceList", this.danceRepository.findAll());

        return super.view("rehearsalDetails", modelAndView);
    }

    @RequestMapping("/rehearsalDetails/deleteDanceFromRehearsal/{danceId}&{rehearsalId}")
    public ModelAndView postDeleteDanceFromRehearsal (@PathVariable Long danceId,
                                                      @PathVariable Long rehearsalId,
                                                      ModelAndView modelAndView) {
        Rehearsal rehearsal = this.rehearsalService.removeDanceFromRehearsal(rehearsalId, danceId);
        modelAndView.addObject("rehearsalDetails", rehearsal);

        return redirect("/rehearsals/rehearsalDetails/"+rehearsalId);
    }

//    @RequestMapping("rehearsalDetails/addDanceToRehearsal/{rehearsalId")
//    public ModelAndView getAddDanceToRehearsal (@PathVariable Long rehearsalId,
//                                                ModelAndView modelAndView) {
//        if (this.rehearsalRepository.findById(rehearsalId).isPresent()) {
//            List<Dance> danceList = this.rehearsalRepository.findById(rehearsalId).get().getDances();
//            modelAndView.addObject("danceList", danceList);
//        }
//
//
//        return super.view("addDanceToRehearsal", modelAndView);
//
//    }

    @RequestMapping("/rehearsalDetails/addDanceToRehearsal")
    public ModelAndView postAddDanceToRehearsal (@RequestParam Long rehearsalId,
                                                 @RequestParam  Long danceId,
                                                 ModelAndView modelAndView){
        //Long rehearsalId = 5L;
        modelAndView.addObject(this.rehearsalService.addDanceToRehearsal(rehearsalId, danceId));

        return redirect("/rehearsals/rehearsalDetails/"+rehearsalId);
    }

    @ModelAttribute ("rehearsalAddForm")
    public RehearsalAddForm rehearsalAddForm() {
        return new RehearsalAddForm();
    }
}
