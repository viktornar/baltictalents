package com.sd.petclinic.pets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sd.petclinic.owners.Owner;
import com.sd.petclinic.owners.OwnerNotFoundException;
import com.sd.petclinic.owners.OwnersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners")
public class PetsRestController {
    @Autowired
    OwnersRepository ownersRepository;

    @Autowired
    PetsRepository petsRespository;

    @GetMapping("/{ownerId}/pets")
    List<Pet> getPets(@PathVariable Long ownerId) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findByIdWithPets(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));
        
        List<Pet> pets = new ArrayList<>(owner.getPets());

        return pets;
    }

    @PostMapping("/{ownerId}/pets")
    List<Pet> postPets(@PathVariable Long ownerId, @RequestBody Pet pet) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));
        pet.setOwner(owner);

        return Arrays.asList(petsRespository.save(pet));
    }
}