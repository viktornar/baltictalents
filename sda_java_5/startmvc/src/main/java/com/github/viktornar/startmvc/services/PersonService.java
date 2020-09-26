package com.github.viktornar.startmvc.services;

import com.github.viktornar.startmvc.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final List<Person> persons = new ArrayList<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public List<Person> getAllPersons() {
      return persons;
    }

    public Person getPersonById(int id) {
        return persons.get(id);
    }

    public void updatePersonById(int id, Person person) {
        persons.set(id, person);
    }

    public void deletePersonById(int id) {
        persons.remove(id);
    }
}
