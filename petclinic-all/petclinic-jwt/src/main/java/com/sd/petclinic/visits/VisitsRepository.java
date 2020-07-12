package com.sd.petclinic.visits;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAll();
    List<Visit> findByPetId(Long petId);
}