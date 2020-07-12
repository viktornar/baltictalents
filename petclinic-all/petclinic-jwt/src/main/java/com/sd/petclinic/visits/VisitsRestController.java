package com.sd.petclinic.visits;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sd.petclinic.pets.PetNotFoundException;
import com.sd.petclinic.pets.PetsRepository;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners/*/pets/")
public class VisitsRestController {
    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    PetsRepository petsRepository;

    @GetMapping("/{petId}/visits")
    List<Visit> getVisits(@PathVariable("petId") Long petId) {
        return visitsRepository.findByPetId(petId);
    }

    @GetMapping("/{petId}/visits/{visitId}")
    List<Visit> getVisit(@PathVariable Long petId, @PathVariable Long visitId)
            throws PetNotFoundException, VisitNotFoundException {

        if (!petsRepository.existsById(petId)) {
            throw new PetNotFoundException(String.format("Pet with id %s not found", petId));
        }

        Visit visit = visitsRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFoundException(String.format("Visit with id %s not found", visitId)));

        return Arrays.asList(visit);
    }

    @PostMapping("/{petId}/visits")
    List<Visit> postVisits(@PathVariable("petId") Long petId, @RequestBody Visit visit) throws PetNotFoundException {
        petsRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));
        visit.setPetId(petId);
        return Arrays.asList(visitsRepository.save(visit));
    }

    @PutMapping("/{petId}/visits/{visitId}")
    List<Visit> putPet(@PathVariable Long petId, @PathVariable Long visitId, @RequestBody Visit visit)
            throws PetNotFoundException, VisitNotFoundException, IllegalAccessException, InvocationTargetException {

        if (!petsRepository.existsById(petId)) {
            throw new PetNotFoundException(String.format("Pet with id %s not found", petId));
        }

        Visit visitToUpdate = visitsRepository.findById(visitId)
                .orElseThrow(() -> new VisitNotFoundException(String.format("Visit with id %s not found", visitId)));

        BeanUtils.copyProperties(visitToUpdate, visit);

        visitToUpdate.setId(visitId);
        visitToUpdate.setPetId(petId);

        return Arrays.asList(visitsRepository.save(visitToUpdate));
    }

    @DeleteMapping("/{petId}/visits/{visitId}")
    List<Visit> deletePet(@PathVariable Long petId, @PathVariable Long visitId)
            throws PetNotFoundException, VisitNotFoundException {
        if (!petsRepository.existsById(petId)) {
            throw new PetNotFoundException(String.format("Pet with id %s not found", petId));
        }

        if (!visitsRepository.existsById(visitId)) {
            throw new VisitNotFoundException(String.format("Visit with id %s not found", visitId));
        }

        visitsRepository.deleteById(visitId);

        return new ArrayList<>();
    }
}