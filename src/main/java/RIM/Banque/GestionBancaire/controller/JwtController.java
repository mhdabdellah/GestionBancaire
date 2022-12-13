package RIM.Banque.GestionBancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import RIM.Banque.GestionBancaire.entity.JwtRequest;
import RIM.Banque.GestionBancaire.entity.JwtResponse;
import RIM.Banque.GestionBancaire.service.JwtService;

@RestController
@CrossOrigin
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping({"/authenticate"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		return jwtService.createJwtToken(jwtRequest);
	}

}
