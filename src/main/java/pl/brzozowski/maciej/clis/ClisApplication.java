package pl.brzozowski.maciej.clis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClisApplication {

    private static final Logger log = LoggerFactory.getLogger(ClisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClisApplication.class, args);
    }
}
