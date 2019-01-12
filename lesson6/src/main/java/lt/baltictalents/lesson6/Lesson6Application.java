package lt.baltictalents.lesson6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Lesson6Application {
    public static void main(String[] args) {
        SpringApplication.run(Lesson6Application.class, args);
    }
}
