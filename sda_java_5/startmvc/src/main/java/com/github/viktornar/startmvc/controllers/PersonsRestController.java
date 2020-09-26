package com.github.viktornar.startmvc.controllers;

import com.github.viktornar.startmvc.models.Person;
import com.github.viktornar.startmvc.services.PersonService;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PersonsRestController extends ApiRestController {
    private PersonService personService;

    public PersonsRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    List<Person> getAllPersons(HttpServletRequest request, SecurityContextProvider ctx) {
        System.out.println(ctx);
        return this.personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    Person getPersonById(@PathVariable int id) {
        return this.personService.getPersonById(id);
    }

    @PutMapping("/persons/{id}")
    List<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        this.personService.updatePersonById(id, person);
        return this.personService.getAllPersons();
    }

    @PostMapping("/persons")
    List<Person> createPersons(@RequestBody Person person) {
        this.personService.addPerson(person);
        return this.personService.getAllPersons();
    }

    @DeleteMapping("/persons/{id}")
    List<Person> deletePerson(@PathVariable int id) {
        this.personService.deletePersonById(id);
        return this.personService.getAllPersons();
    }
}
