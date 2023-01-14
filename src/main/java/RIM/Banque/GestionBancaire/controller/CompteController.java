package RIM.Banque.GestionBancaire.controller;

import java.util.List;

import RIM.Banque.GestionBancaire.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//
//import com.springboot.blog.entity.Compte;
//import com.springboot.blog.payload.ConsulteDto;
//import com.springboot.blog.payload.OperationDto;
//import com.springboot.blog.payload.VirementDto;
//import com.springboot.blog.service.CompteService;

import RIM.Banque.GestionBancaire.dto.comptes.ConsulteCompteDto;
import RIM.Banque.GestionBancaire.dto.comptes.OperationOnComptDto;
import RIM.Banque.GestionBancaire.dto.comptes.PaymentDto;
import RIM.Banque.GestionBancaire.dto.comptes.VirementDto;
import RIM.Banque.GestionBancaire.entity.Carte;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.service.CarteService;
import RIM.Banque.GestionBancaire.service.CompteService;
//localhost:9000/comptes
import RIM.Banque.GestionBancaire.service.OperationService;

@CrossOrigin(origins = "*")
@RestController
// @RequestMapping("/comptesmangement")
public class CompteController {
	@Autowired
	private CompteService compteService;

	@Autowired
	private CarteService carteService;

	@RequestMapping(method = RequestMethod.POST, value = "/test")
	public Object yyy() {
		String test = "Hello and w46ye4 w4yw4y4y the ywyey htry5";
		return ResponseEntity.status(HttpStatus.OK).body(test);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ouvertureCompte")
	public Object setCompte(@RequestBody Compte compte) {
		compteService.save(compte);
		return ResponseEntity.status(HttpStatus.OK).body("la compte est bien Cree");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/searchcompte/{codecompte}")
	public Object searchcompte(@PathVariable Long codecompte) {
		Compte compte = compteService.getCompteByCode(codecompte);
		return ResponseEntity.status(HttpStatus.OK).body(compte);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/comptes")
	@CrossOrigin(origins = "*")
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

	// @PostMapping("paymentEnLigne") // codeCompte montant date => historique
	// VersementDto
	// public Object paymentEnLigne(@RequestBody PaymentDto paymentDto) {
	// Carte carte = carteService.getCarteByNumero(paymentDto.getNumeroCarte());
	// Compte compteSource = compteService.getCompteByCarte(carte);
	// Compte compteDestination =
	// compteService.getCompteByCode(paymentDto.getCodeCompteDestination());
	// double compteSourceSolde = compteSource.getSolde();
	// double compteDestinationSolde = compteDestination.getSolde();
	// double montant = paymentDto.getMontant();
	// String message;
	// if (montant < compteSourceSolde) {
	// double compteSourcefinalSold = compteSourceSolde - montant;
	// compteSource.setSolde(compteSourcefinalSold);
	// compteService.save(compteSource);
	// double compteDestinationfinalSold = compteDestinationSolde + montant;
	// compteDestination.setSolde(compteDestinationfinalSold);
	// compteService.save(compteDestination);

	// message = "un virment de " + montant + "vers le client " +
	// compteSource.getClient().getFirstName() + ' '
	// + compteSource.getClient().getLastName() + "a ete bien effectuée";
	// } else {
	// message = "ce montant n'est pas dans le Compte pour effectuer un payment ";
	// }

	// return ResponseEntity.status(HttpStatus.OK).body(message);
	// }

	// @GetMapping("/historique") // compte
	// public Object getHistoriqueCompte() {
	// List<Compte> comptes = compteService.getComptes();
	// return ResponseEntity.status(HttpStatus.OK).body(comptes);
	// }

}
