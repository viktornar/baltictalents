package com.github.viktornar.startmvc.controllers;

import com.github.viktornar.startmvc.models.Person;
import com.github.viktornar.startmvc.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persons")
public class PersonsController {
    PersonService personService;

    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    String getPersonsList(Model model) {
        model.addAttribute(
                "persons",
                this.personService.getAllPersons()
        );

        return "persons";
    }

    @GetMapping("/new")
    String getNewPersonForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "newPersonForm";
    }

    @PostMapping("/new")
    String createNewPerson(Person person, Model model) {
        this.personService.addPerson(person);
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/{id}")
    String getUserById(@PathVariable int id, Model model) {
        Person person = this.personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/{id}/update")
    String geUpdateUserForm(@PathVariable int id, Model model) {
        Person person = this.personService.getPersonById(id);
        model.addAttribute("person", person);
        return "updatePersonForm";
    }

    @PostMapping("/{id}/update")
    String updatePerson(@PathVariable int id, Person person, Model model) {
        this.personService.updatePersonById(id, person);
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/delete")
    String getPersonDeleteForm() {
        System.out.println("Test");
        return "personDeleteForm";
    }

    @DeleteMapping("/delete")
    String deletePerson() {
        System.out.println("Invoking this method");
        return "redirect:persons";
    }
}
