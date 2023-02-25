package RIM.Banque.GestionBancaire.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIM.Banque.GestionBancaire.entity.Carte;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.CompteRepository;
import RIM.Banque.GestionBancaire.repository.UserRepository;

@Service
@Transactional
public class CompteService {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private UserRepository userRepository;

	public Compte save(Compte compte) {

		return compteRepository.save(compte);
	}

	public List<Compte> getComptes() {
		return compteRepository.findAll();
	}

	public Compte getCompteByCode(Long codeCompte) {
		Compte compte = compteRepository.findByCodeCompte(codeCompte);
		if (compte == null)
			throw new RuntimeException("Compte est introuvable");
		return compte;
	}

	public Set<Compte> getCompteByUser(Long idUser) {
		User user = userRepository.findById(idUser).get();
		if (user != null) {
			// Compte compte = compteRepository.findByClient(user);
			Set<Compte> comptes = user.getComptes();
			if (comptes == null)
				throw new RuntimeException("Compte est introuvable");
			return comptes;
		} else {
			throw new RuntimeException("User est introuvable");
		}
	}

	// public Compte getCompteByCarte(Carte carte) {
	// return compteRepository.findByCarte(carte);
	// }

}