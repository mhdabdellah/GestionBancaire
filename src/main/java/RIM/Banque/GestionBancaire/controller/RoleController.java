package RIM.Banque.GestionBancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIM.Banque.GestionBancaire.entity.Role;
import RIM.Banque.GestionBancaire.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rolesmangement")

public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	
	
	@PostMapping("/newrole")
	public ResponseEntity<?> newrole(@RequestBody Role role) {
		
		Role response = roleService.createNewRole(role);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
