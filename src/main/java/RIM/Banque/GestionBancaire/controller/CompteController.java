package RIM.Banque.GestionBancaire.controller;

import java.util.List;
import java.util.Set;

import RIM.Banque.GestionBancaire.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@RequestMapping("/comptesmangement/")
public class CompteController {
	@Autowired
	private CompteService compteService;

	@Autowired
	private CarteService carteService;

	@RequestMapping(method = RequestMethod.POST, value = "test")
	public Object yyy() {
		String test = "Hello and w46ye4 w4yw4y4y the ywyey htry5";
		return ResponseEntity.status(HttpStatus.OK).body(test);
	}

	@RequestMapping(method = RequestMethod.POST, value = "ouvertureCompte")
	public Object setCompte(@RequestBody Compte compte) {
		compteService.save(compte);
		return ResponseEntity.status(HttpStatus.OK).body("la compte est bien Cree");
	}

	@RequestMapping(method = RequestMethod.GET, value = "searchcompte/{codecompte}")
	public Object searchcompte(@PathVariable Long codecompte) {
		Compte compte = compteService.getCompteByCode(codecompte);
		return ResponseEntity.status(HttpStatus.OK).body(compte);
	}

	@RequestMapping(method = RequestMethod.GET, value = "comptes")
	@CrossOrigin(origins = "*")
	public Object getCompte() {
		List<Compte> comptes = compteService.getComptes();
		return ResponseEntity.status(HttpStatus.OK).body(comptes);
	}

	@PostMapping("getCompteByUser")
	public Object getCompteByUser(@RequestBody Long idUser) {
		Set<Compte> comptes = compteService.getCompteByUser(idUser);
		return ResponseEntity.status(HttpStatus.OK).body(comptes);
	}

	@GetMapping("consulterSold")
	public Object getAcountSold(@RequestBody ConsulteCompteDto consulteDto) {
		Compte compte = compteService.getCompteByCode(consulteDto.getCodeCompte());
		double curentSolde = compte.getSolde();
		return ResponseEntity.status(HttpStatus.OK).body(curentSolde);
	}

	@GetMapping("getCartesByCompte") // cartes
	public Object getcartesByCompte(@RequestBody Long codeCompte) {
		Compte compte = compteService.getCompteByCode(codeCompte);
		List<Carte> cartes = carteService.getCarteByCompte(compte);
		return ResponseEntity.status(HttpStatus.OK).body(cartes);
	}

	@GetMapping("getCartes") // cartes
	public Object getcartes() {
		List<Carte> cartes = carteService.getAllCartes();
		return ResponseEntity.status(HttpStatus.OK).body(cartes);
	}

	@GetMapping("deleteCartes") // cartes
	public Object deleteCarte(@RequestBody long numero) {
		Carte carte = carteService.getCarteByNumero(numero);
		boolean result = carteService.deleteCarte(carte);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
