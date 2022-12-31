package RIM.Banque.GestionBancaire;

import RIM.Banque.GestionBancaire.controller.CompteController;
import RIM.Banque.GestionBancaire.dto.users.RegisterDto;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.CompteRepository;
import RIM.Banque.GestionBancaire.service.CompteService;
import RIM.Banque.GestionBancaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GestionBancaireApplication implements CommandLineRunner {
@Autowired
	CompteService compteService;
@Autowired
CompteController compteController;
@Autowired
UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(GestionBancaireApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		RegisterDto redta =new RegisterDto("ahmed",
//				"sidi","hcn","hacen@gmail.com","49134106","c14466",false);
//
//	User c1 =new User();
//		User user1=UserService.registerNewUser(redta);

//Compte cp0= new Compte(123,new Date(),"non active",500000,user1,);
//	 Compte cp1= compteService.save( );
//				new CompteCourant("c1",new Date(),90000,c1,6000));
//





	}
}
