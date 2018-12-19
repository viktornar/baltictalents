package lt.baltictalents.lesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class Lesson3Application {
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Lesson3Application.class, args);
    }
}

