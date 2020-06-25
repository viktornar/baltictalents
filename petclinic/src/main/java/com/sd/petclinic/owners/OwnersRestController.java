package com.sd.petclinic.owners;

import com.sd.petclinic.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    MockedOwnersRepository mockedOwnersRepository;

    @Autowired
    OwnersRepository ownersRepository;

    @GetMapping("/owners")
    List<Owner> getOwners() {
        return ownersRepository.findAll();
    }

    @GetMapping("/owners/{id}")
    List<Person> getOwner(@PathVariable Long id) {
        return mockedOwnersRepository.getOwnerById(id);
    }

    @PostMapping("/owners")
    List<Owner> postOwner(@RequestBody Owner owner) {
        return Arrays.asList(ownersRepository.save(owner));
    }

    @PutMapping("/owners/{id}")
    List<Person> putOwner(@RequestBody Person person, @PathVariable Long id) {
        return mockedOwnersRepository.updateOwner(person, id);
    }

    @DeleteMapping("/owners/{id}")
    List<Owner> deleteOwner(@PathVariable Long id) {
        ownersRepository.deleteById(id);
        return new ArrayList<Owner>();
    }
}
