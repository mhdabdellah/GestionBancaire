package RIM.Banque.GestionBancaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RIM.Banque.GestionBancaire.entity.Role;
import RIM.Banque.GestionBancaire.repository.RoleRepository;

@Service
public class RoleService {
	
	
	@Autowired
	RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
		return roleRepository.save(role);
	}

}
