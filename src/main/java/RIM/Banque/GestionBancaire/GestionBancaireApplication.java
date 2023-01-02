package RIM.Banque.GestionBancaire;

import RIM.Banque.GestionBancaire.controller.CompteController;
import RIM.Banque.GestionBancaire.dto.users.RegisterDto;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.Operation;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.CompteRepository;
import RIM.Banque.GestionBancaire.service.CompteService;
import RIM.Banque.GestionBancaire.service.OperationService;
import RIM.Banque.GestionBancaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Set;

import static RIM.Banque.GestionBancaire.entity.StatuesCompte.ACTIVATED;
import static RIM.Banque.GestionBancaire.entity.StatuesCompte.CREATED;
import static RIM.Banque.GestionBancaire.entity.TypeOperation.RETRAIT;
import static RIM.Banque.GestionBancaire.entity.TypeOperation.VIRMENT;

@SpringBootApplication
public class GestionBancaireApplication implements CommandLineRunner {
@Autowired
	CompteService compteService;
@Autowired
	OperationService operationService;

@Autowired
UserService userService;

	public static void main(String[] args) {

		SpringApplication.run(GestionBancaireApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RegisterDto redta1 =new RegisterDto("ahmed",
				"sidi","hcn","hacen@gmail.com","49134106","c14466",false);
		RegisterDto redta2 =new RegisterDto("ahmed",
				"aly","baba","bb@gmail.com","49134106","c14466",true);

		User user1=userService.registerNewUser(redta1);
		User user2=userService.registerNewUser(redta2);


	 Compte cp1= compteService.save(new Compte(123L,new Date(),"non active",500000,user1,ACTIVATED));
	 Compte cp2= compteService.save(new Compte(123L,new Date(),"non active",500000,user2,ACTIVATED));
		Compte cp3= compteService.save(new Compte(123L,new Date(),"non active",10000000,user1,CREATED));

	Operation op1=operationService.save(new Operation(1L,"0012l",2500000,"",new Date(),cp1,RETRAIT));
		Operation op2=operationService.save(new Operation(2L,"0013l",2000,"",new Date(),cp2,VIRMENT ));






	}
}
