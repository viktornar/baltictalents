package com.sd.petclinic.pets;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sd.petclinic.model.NamedEntity;
import com.sd.petclinic.owners.Owner;
import com.sd.petclinic.visit.Visit;

// import org.springframework.beans.support.MutableSortDefinition;
// import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    // @ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn(name = "type_id")
    // private PetType type;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petId", fetch = FetchType.EAGER)
    private Set<Visit> visits = new LinkedHashSet<>();

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public List<Visit> getVisits() {
        return new ArrayList(visits);
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
