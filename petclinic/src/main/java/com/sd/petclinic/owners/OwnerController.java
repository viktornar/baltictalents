package com.sd.petclinic.owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {
    @Autowired
    OwnersRepository ownersRepository;

    @GetMapping("/owners")
    String showOwners() {
        return "owners/list";
    }
}