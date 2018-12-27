package lt.baltictalents.lesson5.repository;

import lt.baltictalents.lesson5.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("personRepository")
public interface PersonRepository extends CrudRepository<Person, Long>, JpaSpecificationExecutor<Person> {
  Person findByName(String name);
}
