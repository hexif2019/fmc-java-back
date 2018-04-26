package fr.insa.fmc.javaback;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaBackApplication  {


	public static void main(String[] args) {
		SpringApplication.run(JavaBackApplication.class, args);
	}


}
