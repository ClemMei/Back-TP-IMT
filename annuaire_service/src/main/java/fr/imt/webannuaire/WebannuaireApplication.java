package fr.imt.webannuaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebannuaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebannuaireApplication.class, args);
	}

}
