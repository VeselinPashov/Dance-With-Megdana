package com.Megdana.Dance.with.Megdana.services;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.RehearsalAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.RehearsalModel;
import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import com.Megdana.Dance.with.Megdana.domain.entities.Rehearsal;
import com.Megdana.Dance.with.Megdana.repositories.RehearsalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RehearsalService {
    private final RehearsalRepository rehearsalRepository;

    private final ModelMapper modelMapper;

    public RehearsalService(RehearsalRepository rehearsalRepository, ModelMapper modelMapper) {
        this.rehearsalRepository = rehearsalRepository;
        this.modelMapper = modelMapper;
    }

    public void saveNewRehearsal (RehearsalAddForm rehearsalAddForm) {
        int danceDuration = rehearsalAddForm.getDances()
                .stream()
                .mapToInt(Dance::getDuration)
                .sum();
        rehearsalAddForm.setDurationInSeconds(danceDuration);
        this.rehearsalRepository.saveAndFlush(this.modelMapper.map(rehearsalAddForm, Rehearsal.class));
    }

    public void editRehearsal(RehearsalModel rehearsalModel) {
        Optional<Rehearsal> rehearsalToEdit = this.rehearsalRepository.findById(rehearsalModel.getId());
        if (rehearsalToEdit.isPresent()) {
            rehearsalToEdit.get().setDate(rehearsalModel.getDate());
            rehearsalToEdit.get().setLocation(rehearsalModel.getLocation());
            this.rehearsalRepository.saveAndFlush(rehearsalToEdit.get());
        }
    }
}
