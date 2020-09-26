package com.sd.petclinic.pets;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sd.petclinic.owners.Owner;
import com.sd.petclinic.owners.OwnerNotFoundException;
import com.sd.petclinic.owners.OwnersRepository;

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
@RequestMapping("/api/owners")
public class PetsRestController {
    @Autowired
    private OwnersRepository ownersRepository;

    @Autowired
    private PetsRepository petsRespository;

    @GetMapping("/{ownerId}/pets")
    List<Pet> getPets(@PathVariable Long ownerId) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findByIdWithPets(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));

        List<Pet> pets = new ArrayList<>(owner.getPets());

        return pets;
    }

    @GetMapping("/{ownerId}/pets/{petId}")
    List<Pet> getPet(@PathVariable Long ownerId, @PathVariable Long petId)
            throws OwnerNotFoundException, PetNotFoundException {
        
        ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));
        
        Pet pet = petsRespository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));

        return Arrays.asList(pet);
    }

    @PostMapping("/{ownerId}/pets")
    List<Pet> postPet(@PathVariable Long ownerId, @RequestBody Pet pet) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));
        pet.setOwner(owner);

        return Arrays.asList(petsRespository.save(pet));
    }

    @PutMapping("/{ownerId}/pets/{petId}")
    List<Pet> putPet(@PathVariable Long ownerId, @PathVariable Long petId, @RequestBody Pet pet)
            throws OwnerNotFoundException, PetNotFoundException, IllegalAccessException, InvocationTargetException {
        Owner owner = ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));

        Pet petToUpdate = petsRespository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));

        BeanUtils.copyProperties(petToUpdate, pet);
        
        petToUpdate.setId(petId);
        petToUpdate.setOwner(owner);

        return Arrays.asList(petsRespository.save(petToUpdate));
    }

    @DeleteMapping("/{ownerId}/pets/{petId}")
    List<Pet> deletePet(@PathVariable Long ownerId, @PathVariable Long petId) throws OwnerNotFoundException,
            PetNotFoundException {
        ownersRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", ownerId)));

        petsRespository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(String.format("Pet with id %s not found", petId)));
        
        petsRespository.deleteById(petId);
        return new ArrayList<Pet>();
    }
}