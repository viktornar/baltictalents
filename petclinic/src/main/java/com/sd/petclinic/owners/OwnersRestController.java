package com.sd.petclinic.owners;

import com.sd.petclinic.model.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
@RequestMapping("/api")
public class OwnersRestController {
    @Autowired
    OwnersRepository ownersRepository;

    @GetMapping("/owners")
    List<Owner> getOwners() {
        return ownersRepository.findAll();
    }

    @GetMapping("/owners/{id}")
    List<Owner> getOwner(@PathVariable Long id) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", id)));
        return Arrays.asList(owner);
    }

    @PostMapping("/owners")
    List<Owner> postOwner(@RequestBody Owner owner) {
        return Arrays.asList(ownersRepository.save(owner));
    }

    @PutMapping("/owners/{id}")
    List<Owner> putOwner(@RequestBody Owner owner, @PathVariable Long id)
            throws IllegalAccessException, InvocationTargetException, OwnerNotFoundException {
        Owner ownerForUpdate = ownersRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", id)));
        owner.setId(id);
        BeanUtils.copyProperties(ownerForUpdate, owner);
        ownersRepository.save(ownerForUpdate);

        return Arrays.asList(ownerForUpdate);
    }

    @DeleteMapping("/owners/{id}")
    List<Owner> deleteOwner(@PathVariable Long id) {
        ownersRepository.deleteById(id);
        return new ArrayList<Owner>();
    }
}
