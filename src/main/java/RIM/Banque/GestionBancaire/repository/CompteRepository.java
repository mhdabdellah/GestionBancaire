package RIM.Banque.GestionBancaire.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Compte;


public interface CompteRepository extends JpaRepository<Compte, Long>{
    Compte getByCodeCompte(Long codeCompte);
}
