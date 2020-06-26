package com.sd.petclinic.visit;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VisitsRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAll();
    List<Visit> findByPetId(Long petId);
}