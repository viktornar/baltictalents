package com.sd.petclinic.owners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sd.petclinic.model.Person;

import org.springframework.stereotype.Component;

// Will be real repository in future.
@Component
public class MockedOwnersRepository {
    List<Person> persons = new ArrayList<Person>();

    List<Person> getOwners() {
        return persons;
    }

	List<Person> addOwner(Person person) {
        persons.add(person);
        return persons;
	}

	List<Person> getOwnerById(Long id) {
		return Arrays.asList(persons.get(id.intValue()));
	}

	List<Person> updateOwner(Person person, Long id) {
        persons.add(id.intValue(), person);
        persons.remove(id.intValue() + 1);
		return persons;
	}

	public List<Person> deleteOwner(Long id) {
        persons.remove(id.intValue());
        return persons;
	}
}