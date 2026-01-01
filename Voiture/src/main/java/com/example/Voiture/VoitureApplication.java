package com.example.Voiture;

import com.example.Voiture.entities.Voiture;
import com.example.Voiture.models.Client;
import com.example.Voiture.repositories.VoitureRepository;
import com.example.Voiture.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class VoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}

	@Bean
	CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository, ClientService clientService) {
		return args -> {
			Client c1 = clientService.clientById(2L);
			Client c2 = clientService.clientById(1L);
			System.out.println("**************************");
			System.out.println("Id est :" + c2.getId());
			System.out.println("Nom est :" + c2.getNom());
			System.out.println("**************************");
			System.out.println("**************************");
			System.out.println("Id est :" + c1.getId());
			System.out.println("Nom est :" + c1.getNom());
			System.out.println("Age est :" + c1.getAge());
			System.out.println("**************************");
			
			Voiture v1 = new Voiture();
			v1.setMarque("Toyota");
			v1.setMatricule("A 25 333");
			v1.setModel("Corolla");
			v1.setId_client(1L);
			v1.setClient(c2);
			
			Voiture v2 = new Voiture();
			v2.setMarque("Renault");
			v2.setMatricule("B 6 3456");
			v2.setModel("Megane");
			v2.setId_client(1L);
			v2.setClient(c2);
			
			Voiture v3 = new Voiture();
			v3.setMarque("Peugeot");
			v3.setMatricule("A 55 4444");
			v3.setModel("301");
			v3.setId_client(2L);
			v3.setClient(c1);
			
			voitureRepository.save(v1);
			voitureRepository.save(v2);
			voitureRepository.save(v3);
		};
	}

}
