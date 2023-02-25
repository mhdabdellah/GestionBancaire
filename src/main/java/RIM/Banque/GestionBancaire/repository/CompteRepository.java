package RIM.Banque.GestionBancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Carte;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.User;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    // Compte getByCodeCompte(Long codeCompte);
    Compte findByCodeCompte(Long codeCompte);
    Compte findByClient(User user);

    // Compte findByCarte(Carte carte);
}
