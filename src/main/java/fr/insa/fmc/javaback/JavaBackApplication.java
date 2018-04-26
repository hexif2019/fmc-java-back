package fr.insa.fmc.javaback;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaBackApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(JavaBackApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception{
		//repository.deleteAll();
		System.out.println("123");

		//repository.save(new Client("insa@lyon","yuan","ye","123"));
		//for(Client client:repository.findAll()){
		//	System.out.println(client);
		//}
	}
}
