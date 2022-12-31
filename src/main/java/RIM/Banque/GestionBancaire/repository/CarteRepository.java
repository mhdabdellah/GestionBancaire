package RIM.Banque.GestionBancaire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Carte;

public interface CarteRepository extends JpaRepository<Carte, Long> {

    Optional<Carte> findByNumero(Long numero);

    Optional<Carte> findByCode(String code);

}
