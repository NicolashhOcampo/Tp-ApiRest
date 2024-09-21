package org.example;


import jakarta.transaction.Transactional;
import org.example.entities.*;
import org.example.repositories.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Inicial1Application {
	private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

	@Autowired
	private PersonaRepository personaRepository;
	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);

		System.out.println("funcionando");
	}




	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository) {
		return args -> {
			// Creo un objeto persona
			Persona per1 = Persona.builder().
			nombre("Nicolas").apellido("Ocampo").dni(47349).
			build();

			Localidad localidad1= Localidad.builder()
					.denominacion("Lujan de Cuyo")
					.build();

			Domicilio dom1 = Domicilio.builder().
					calle("Suipacha")
					.numero(239)
					.localidad(localidad1)
					.build();

			localidad1.getDomicilios().add(dom1);
			per1.setDomicilio(dom1);

			personaRepository.save(per1);

			// Creo otra persona
			Persona per2 = Persona.builder().
					nombre("Alicia").apellido("Calderon").dni(12345).
					build();
/*
			Domicilio dom2 = Domicilio.builder().
					calle("Lulunta").
					numero(339).build();

			per2.setDomicilio(dom2);

*/
			Libro libro1= Libro.builder()
							.titulo("Analisis 1")
							.fecha(12)
							.genero("Estudio")
							.paginas(54)
							.persona(per1)
							.build();

			per1.getLibros().add(libro1);

			Autor autor1= Autor.builder()
							.nombre("Juan")
							.apellido("Cruz")
							.biografia("fjls fsf")
							.build();

			libro1.getAutores().add(autor1);
			autor1.getLibros().add(libro1);


			// Lo grabo a través del repositorio de Spring
			personaRepository.save(per2);

			List<Persona> recuperadas = personaRepository.findAll();
			System.out.println(recuperadas);

			logger.info("Detalles de la persona: {}", recuperadas);




			Optional<Persona> recuperada = personaRepository.findById(1L);
			System.out.println(recuperada);

			logger.info("Detalles de la persona: {}", recuperada);


			//dom1.setCalle("Rodriguezaaaa");

			personaRepository.save(per1);




		};

		};




}
