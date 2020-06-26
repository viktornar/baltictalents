package com.sd.petclinic.pets;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetsRepository extends CrudRepository<Pet, Long> {
    List<Pet> findAll();
}