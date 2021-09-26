package com.assessment.betbull.betbull;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableSwagger2
public class BetbullApplication {

  public static void main(String[] args) {
    SpringApplication.run(BetbullApplication.class,
                          args);
  }

}
