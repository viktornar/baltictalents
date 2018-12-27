package lt.baltictalents.lesson5;

import lt.baltictalents.lesson5.model.Person;
import lt.baltictalents.lesson5.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebMvc
public class Lesson5Application {
    public static void main(String[] args) {
        SpringApplication.run(Lesson5Application.class, args);
    }
}

