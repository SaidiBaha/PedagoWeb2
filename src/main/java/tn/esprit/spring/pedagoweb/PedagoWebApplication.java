package tn.esprit.spring.pedagoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"tn.esprit.spring.entity"})
@EnableAspectJAutoProxy
@EnableCaching

@ComponentScan(basePackages = {"tn.esprit.spring.controllers","tn.esprit.spring.services","tn.esprit.spring.configuration"})
@EnableJpaRepositories(basePackages = {"tn.esprit.spring.repositories"})
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PedagoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedagoWebApplication.class, args);
    }

}
