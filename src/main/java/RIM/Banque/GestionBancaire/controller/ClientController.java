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

import RIM.Banque.GestionBancaire.entity.Client;
import RIM.Banque.GestionBancaire.service.ClientService;



@CrossOrigin("*")
@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/ajouter")
	public Object setClien(@RequestBody Client client) {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.saveClient(client));
	}
	
	@GetMapping("/clients")
	public Object getClien() {
		List<Client> clients =  clientService.getClients();
		return ResponseEntity.status(HttpStatus.OK).body(clients);
	}

}//
