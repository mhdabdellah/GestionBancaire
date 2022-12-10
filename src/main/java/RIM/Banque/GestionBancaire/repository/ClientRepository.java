package RIM.Banque.GestionBancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
