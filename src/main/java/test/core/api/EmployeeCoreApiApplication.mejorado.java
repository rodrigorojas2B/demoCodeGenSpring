package test.core.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmployeeCoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeCoreApiApplication.class, args);
	}

}
