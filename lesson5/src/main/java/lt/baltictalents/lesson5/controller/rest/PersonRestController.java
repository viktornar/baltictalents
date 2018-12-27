package lt.baltictalents.lesson5.controller.rest;

import lt.baltictalents.lesson5.model.Person;
import lt.baltictalents.lesson5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonRestController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/persons")
    List<Person> getPersons() {
       return (List<Person>)personRepository.findAll();
    }
}
