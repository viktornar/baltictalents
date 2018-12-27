package lt.baltictalents.lesson5;

import lombok.val;
import lt.baltictalents.lesson5.helper.JDBCHelper;
import lt.baltictalents.lesson5.model.Person;
import lt.baltictalents.lesson5.repository.PersonRepository;
import lt.baltictalents.lesson5.repository.PersonSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@EnableJpaRepositories
public class JPAApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPAApplication.class);

    @Bean
    public DataSource dataSource() {
        val jdbcHelper = new JDBCHelper(
                "root",
                "",
                "mysql",
                "localhost",
                "33060",
                "lesson5"
        );

        return jdbcHelper.getMysqlDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("lt.baltictalents.lesson5.model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());

        return factory;
    }

    /**
     * Properties for Jpa
     */
    private static Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    static public void main(String ...args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                JPAApplication.class);

        PersonRepository repository = context.getBean(PersonRepository.class);

        Person peter = new Person("Peter", "Sagan", 17);
        Person nasta = new Person("Nasta", "Kuzminova", 25);
        Person john = new Person("John", "lawrence", 35);
        Person terry = new Person("Terry", "Law", 36);

        // Add new Person records
        repository.save(peter);
        repository.save(nasta);
        repository.save(john);
        repository.save(terry);

        // Count Person records
        LOGGER.info("Count Person records: {}", repository.count());

        // Print all records
        List<Person> persons = (List<Person>) repository.findAll();
        for (Person person : persons) {
            LOGGER.info(person.toString());
        }

        // Update Person
        nasta.setName("Barbora");
        nasta.setSurname("Spotakova");
        repository.save(nasta);

        LOGGER.info("Find by name Barbora : {}", repository.findByName("Barbora"));

        LOGGER.info("Find by id 2: {}", repository.findById(2L));

        // Remove record from Person
        repository.deleteById(2L);

        // count records
        LOGGER.info("Count Person records: {}", repository.count());

        // find by name
        Optional<Person> p = repository.findOne(new PersonSpecifications.NameEqualSpec("John"));
        LOGGER.info("Find by John is {}", p);

        // find by age
        persons = repository.findAll(new PersonSpecifications.AgeBetweenSpec(20, 40));

        LOGGER.info("Find Person with age between 20,40: ");
        for (Person person : persons) {
            LOGGER.info(person.toString());
        }

        context.close();
    }
}
