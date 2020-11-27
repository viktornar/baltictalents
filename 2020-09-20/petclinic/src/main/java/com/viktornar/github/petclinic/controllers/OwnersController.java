package com.viktornar.github.petclinic.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {
    @Autowired
    OwnersRepository ownersRepository;

    @GetMapping("/owners")
    String showOwners(Map<String, Object> model) {
        List<Owner> owners = ownersRepository.findAll();
        if (owners.size() == 0) {
            return "redirect:owners/new";
        }
        model.put("owners", owners);
        return "owners/list";
    }

    @GetMapping("/owners/new")
    String createOwnerShow(Map<String, Object> model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return "owners/createOrUpdate";
    }

    @PostMapping("/owners/new")
    String createOwnerCreate(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdate";
        } else {
            ownersRepository.save(owner);
            return "redirect:/owners";
        }
    }

    @GetMapping("/owners/{id}")
    String updateOwnerShow(@PathVariable("id") Long id, Model model) throws OwnerNotFoundException {
        Owner owner = ownersRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Owner with id %s not found", id)));

        model.addAttribute(owner);

        return "owners/createOrUpdate";
    }

    @PostMapping("/owners/{id}")
    String updateOwnerUpdate(@Valid Owner owner, BindingResult result, @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return "owners/createOrUpdate";
        } else {
            owner.setId(id);
            ownersRepository.save(owner);
            return "redirect:/owners";
        }
    }
}