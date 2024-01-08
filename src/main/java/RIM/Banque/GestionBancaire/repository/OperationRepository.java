package RIM.Banque.GestionBancaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation findByNumeroOperation(String numeroOperation);

    List<Operation> findByCompte(Compte compte);
}
