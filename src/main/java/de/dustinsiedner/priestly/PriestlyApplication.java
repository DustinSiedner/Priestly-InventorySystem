package de.dustinsiedner.priestly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PriestlyApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriestlyApplication.class, args);
  }
}
