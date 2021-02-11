package tezea.si;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiApplication.class, args);
    }

}
