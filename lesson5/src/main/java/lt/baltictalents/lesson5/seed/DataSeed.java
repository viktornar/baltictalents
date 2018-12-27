package lt.baltictalents.lesson5.seed;

import lt.baltictalents.lesson5.model.Person;
import lt.baltictalents.lesson5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Do not use it in production. Only for educational purpose to show how to use
 * posConstruct and PreDestroy. Also it is speed up demonstration of repository usage.
 */
@Component
public class DataSeed {
    @Autowired
    private PersonRepository personRepository;

    // Method invoked during the startup
    @PostConstruct
    public void loadData() {
        Person peter = new Person("Peter", "Sagan", 17);
        Person nasta = new Person("Nasta", "Kuzminova", 25);
        Person john = new Person("John", "lawrence", 35);
        Person terry = new Person("Terry", "Law", 36);

        // Add new Person records
        personRepository.save(peter);
        personRepository.save(nasta);
        personRepository.save(john);
        personRepository.save(terry);
    }

    // Method invoked during the shutdown
    @PreDestroy
    public void removeData() {
        personRepository.deleteAll();
    }
}
