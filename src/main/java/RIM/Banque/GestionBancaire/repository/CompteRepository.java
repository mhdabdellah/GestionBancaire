package RIM.Banque.GestionBancaire.repository;


import RIM.Banque.GestionBancaire.entity.StatuesCompte;
import org.springframework.data.jpa.repository.JpaRepository;


import RIM.Banque.GestionBancaire.entity.Compte;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CompteRepository extends JpaRepository<Compte, Long>{
    // Compte getByCodeCompte(Long codeCompte);
    Compte findByCodeCompte(Long codeCompte);

    List<Compte> findByStatuesCompte(StatuesCompte statu);
}
