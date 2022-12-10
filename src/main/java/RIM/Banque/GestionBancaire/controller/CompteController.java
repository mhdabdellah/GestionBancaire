package RIM.Banque.GestionBancaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//import com.springboot.blog.entity.Compte;
//import com.springboot.blog.payload.ConsulteDto;
//import com.springboot.blog.payload.OperationDto;
//import com.springboot.blog.payload.VirementDto;
//import com.springboot.blog.service.CompteService;

import RIM.Banque.GestionBancaire.dto.comptes.ConsulteCompteDto;
import RIM.Banque.GestionBancaire.dto.comptes.OperationOnComptDto;
import RIM.Banque.GestionBancaire.dto.comptes.VirementDto;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.service.CompteService;


@CrossOrigin("*")
@RestController
@RequestMapping("/comptesmangement")
public class CompteController {
	
	@Autowired
	private CompteService compteService;
	
	@PostMapping("/ouvertirCompte")
	public Object setCompte(@RequestBody Compte compte) {
		compteService.save(compte);
		return ResponseEntity.status(HttpStatus.OK).body("la compte est bien Cree");
	}
	
	
	
	@GetMapping("/comptes")
	public Object getCompte() {
		List<Compte> comptes = compteService.getComptes();
		return ResponseEntity.status(HttpStatus.OK).body(comptes);
	}
	
	@GetMapping("/consulterSold")
	public Object getAcountSold(@RequestBody ConsulteCompteDto consulteDto) {
		Compte compte = compteService.getCompteByCode(consulteDto.getCodeCompte());
		double curentSolde = compte.getSolde();
		return ResponseEntity.status(HttpStatus.OK).body(curentSolde);
	}
	
	
	@PostMapping("/retirerMontant")// codeCompte montant date => historique 
	public Object retirerFromCompte(@RequestBody OperationOnComptDto operationDto) {
		Compte compte = compteService.getCompteByCode(operationDto.getCodeCompte());
		double curentSolde = compte.getSolde();
		double finalSold =  curentSolde - operationDto.getMontant();
		compte.setSolde(finalSold);
		compteService.save(compte);
//		compteService.save(compte);
		return ResponseEntity.status(HttpStatus.OK).body("la compte est bien Cree");
	}
	
	
	
	@PostMapping("/deposerMontant")// codeCompte montant date => historique  VersementDto
	public Object deposerToCompte(@RequestBody OperationOnComptDto operationDto) {
		Compte compte = compteService.getCompteByCode(operationDto.getCodeCompte());
		double curentSolde = compte.getSolde();
		double finalSold =  curentSolde + operationDto.getMontant();
		compte.setSolde(finalSold);
		compteService.save(compte);
		return ResponseEntity.status(HttpStatus.OK).body("la compte est bien Cree");
	}
	
	@PostMapping("/virementMontant")// codeCompte montant date => historique  VersementDto
	public Object VirementMontant(@RequestBody VirementDto virementDto) {
		Compte compteSource = compteService.getCompteByCode(virementDto.getCodeCompteSource());
		Compte compteDestination = compteService.getCompteByCode(virementDto.getCodeCompteDestination());
		
		
		double compteSourceSolde = compteSource.getSolde();
		double compteSourcefinalSold =  compteSourceSolde - virementDto.getMontant();
		compteSource.setSolde(compteSourcefinalSold); 
		compteService.save(compteSource);
		
		double compteDestinationSolde = compteDestination.getSolde();
		double compteDestinationfinalSold =  compteDestinationSolde + virementDto.getMontant();
		compteDestination.setSolde(compteDestinationfinalSold); 
		compteService.save(compteDestination);
		
		return ResponseEntity.status(HttpStatus.OK).body("le virment est bien effectuee");
	}
	
//	@GetMapping("/historique") // compte
//	public Object getHistoriqueCompte() {
//		List<Compte> comptes = compteService.getComptes();
//		return ResponseEntity.status(HttpStatus.OK).body(comptes);
//	}

}

