package com.sd.petclinic.visit;

import java.util.Arrays;
import java.util.List;

import com.sd.petclinic.pets.Pet;
import com.sd.petclinic.pets.PetNotFoundException;
import com.sd.petclinic.pets.PetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners/*/pets/")
public class VisitRestController {
    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    PetsRepository petsRepository;

    @GetMapping("/{petId}/visits")
    List<Visit> getVisits(@PathVariable("petId") Long petId) {
        return visitsRepository.findByPetId(petId);
    }

    @PostMapping("/{petId}/visits")
    List<Visit> postVisits(@PathVariable("petId") Long petId, @RequestBody Visit visit) throws PetNotFoundException {
        petsRepository.findById(petId).orElseThrow(() -> 
            new PetNotFoundException(String.format("Pet with id %s not found", petId)));
        visit.setPetId(petId);
        return Arrays.asList(visitsRepository.save(visit));    
    }
}