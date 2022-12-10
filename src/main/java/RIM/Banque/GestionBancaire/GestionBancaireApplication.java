package RIM.Banque.GestionBancaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GestionBancaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBancaireApplication.class, args);
	}

}
