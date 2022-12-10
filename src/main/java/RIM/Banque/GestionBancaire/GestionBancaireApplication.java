package RIM.Banque.GestionBancaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("RIM.Banque.GestionBancaire.entity")
public class GestionBancaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBancaireApplication.class, args);
	}

}
