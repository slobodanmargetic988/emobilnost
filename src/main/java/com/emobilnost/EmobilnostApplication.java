package com.emobilnost;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*
@EnableJpaRepositories("com.emobilnost.*") 
@EntityScan("com.emobilnost.*")
@ComponentScan("com.emobilnost.*")*/
@SpringBootApplication(/*exclude= {SecurityAutoConfiguration.class}*/)
public class EmobilnostApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmobilnostApplication.class, args);
	}

}
