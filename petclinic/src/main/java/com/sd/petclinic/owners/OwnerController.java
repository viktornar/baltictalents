package com.sd.petclinic.owners;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {
    @Autowired
    OwnersRepository ownersRepository;

    @GetMapping("/owners")
    String showOwners(Map<String, Object> model) {
        List<Owner> owners = ownersRepository.findAll();
        // It is possible to use just plane Map<String, Object> or ModelAndView
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
    String createOwnerUpdate(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdate";
        } else {
            ownersRepository.save(owner);
            return "redirect:/owners";
        }
    }
}