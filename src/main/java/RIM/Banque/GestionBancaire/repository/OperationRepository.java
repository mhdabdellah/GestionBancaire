package RIM.Banque.GestionBancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
