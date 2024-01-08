package RIM.Banque.GestionBancaire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Carte;
import RIM.Banque.GestionBancaire.entity.Compte;

import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, Long> {

    Optional<Carte> findByNumero(Long numero);

    Optional<Carte> findByCode(String code);

    List<Carte> findCarteByCompte(Compte compte);

}
