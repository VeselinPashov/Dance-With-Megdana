package com.Megdana.Dance.with.Megdana.services;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.RehearsalAddForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.RehearsalModel;
import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import com.Megdana.Dance.with.Megdana.domain.entities.Rehearsal;
import com.Megdana.Dance.with.Megdana.repositories.DanceRepository;
import com.Megdana.Dance.with.Megdana.repositories.RehearsalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RehearsalService {
    private final RehearsalRepository rehearsalRepository;

    private final DanceRepository danceRepository;

    private final DanceService danceService;

    private final ModelMapper modelMapper;

    public RehearsalService(RehearsalRepository rehearsalRepository, DanceRepository danceRepository, DanceService danceService, ModelMapper modelMapper) {
        this.rehearsalRepository = rehearsalRepository;
        this.danceRepository = danceRepository;
        this.danceService = danceService;
        this.modelMapper = modelMapper;
    }

    public void saveNewRehearsal (RehearsalAddForm rehearsalAddForm) {
        int danceDuration = rehearsalAddForm.getDances()
                .stream()
                .mapToInt(Dance::getDuration)
                .sum();
        rehearsalAddForm.setDurationInSeconds(danceDuration);
        this.rehearsalRepository.saveAndFlush(this.modelMapper.map(rehearsalAddForm, Rehearsal.class));
        rehearsalAddForm.getDances().forEach(dance -> this.danceService.updateLastDancedDate(dance.getId()));
    }

    public void editRehearsal(RehearsalModel rehearsalModel) {
        Optional<Rehearsal> rehearsalToEdit = this.rehearsalRepository.findById(rehearsalModel.getId());
        if (rehearsalToEdit.isPresent()) {
            rehearsalToEdit.get().setDate(rehearsalModel.getDate());
            rehearsalToEdit.get().setLocation(rehearsalModel.getLocation());
            this.rehearsalRepository.saveAndFlush(rehearsalToEdit.get());
        }
    }

    public Rehearsal removeDanceFromRehearsal (Long rehearsalId, Long danceId) {
        Optional<Rehearsal> currentRehearsal = this.rehearsalRepository.findById(rehearsalId);
        Optional<Dance> danceToRemove = this.danceRepository.findById(danceId);
        if (currentRehearsal.isEmpty() || danceToRemove.isEmpty()) {
            throw new IllegalArgumentException("Either rehearsal or dance doesn't exist");
        }
        Rehearsal rehearsal = currentRehearsal.get();
        List<Dance> dances = rehearsal.getDances();
        dances.removeIf(dance -> Objects.equals(dance.getId(), danceId));
        rehearsal.setDances(dances);
        this.rehearsalRepository.saveAndFlush(rehearsal);


        return rehearsal;
    }

    public Rehearsal addDanceToRehearsal (Long rehearsalId, Long danceId) {
        Optional<Rehearsal> currentRehearsal = this.rehearsalRepository.findById(rehearsalId);
        Optional<Dance> danceToAdd = this.danceRepository.findById(danceId);
        if (currentRehearsal.isEmpty() || danceToAdd.isEmpty()) {
            throw new IllegalArgumentException("Either rehearsal or dance doesn't exist");
        }
        Rehearsal rehearsal = currentRehearsal.get();
        List<Dance> dances = rehearsal.getDances();
        boolean danceInRehearsal = dances.stream()
                .anyMatch(dance -> Objects.equals(dance.getId(), danceToAdd.get().getId()));
        if (!danceInRehearsal) {
            dances.add(danceToAdd.get());
            rehearsal.setDances(dances);
            this.rehearsalRepository.saveAndFlush(rehearsal);
        }

        this.danceService.updateLastDancedDate(danceToAdd.get().getId());

        return rehearsal;
    }
}
