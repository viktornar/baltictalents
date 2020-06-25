package com.sd.petclinic.owners;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnersRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAll();
    
}