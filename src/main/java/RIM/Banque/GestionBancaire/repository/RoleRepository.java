package RIM.Banque.GestionBancaire.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import RIM.Banque.GestionBancaire.entity.Role;

// @Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	Optional<Role> findByName(String name);
	
}
