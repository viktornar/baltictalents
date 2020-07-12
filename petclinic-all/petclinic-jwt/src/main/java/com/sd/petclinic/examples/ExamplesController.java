package com.sd.petclinic.examples;

import java.util.List;

import com.sd.petclinic.owners.Owner;
import com.sd.petclinic.owners.OwnersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/examples")
public class ExamplesController {
    @Autowired
    OwnersRepository ownersRepository;

    @GetMapping("/table")
    String getTable(Model model) {
        List<Owner> owners = ownersRepository.findAll();
        model.addAttribute("owners", owners);
        return "/examples/table";
    }

    @GetMapping("/tableWithLayout")
    String getTableWithLayout(Model model) {
        List<Owner> owners = ownersRepository.findAll();
        model.addAttribute("owners", owners);
        return "/examples/tableWithLayout";
    }
    
}