package RIM.Banque.GestionBancaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.repository.CompteRepository;


@Service
@Transactional
public class CompteService {

	
	@Autowired
	private CompteRepository compteRepository;
	
	
	public void save(Compte compte) {
		compteRepository.save(compte);
	}
	
	public  List<Compte> getComptes() {
		return compteRepository.findAll();
	}

	public  Compte getCompteByCode(Long codeCompte) {

		return compteRepository.findByCodeCompte(codeCompte);
	}


}