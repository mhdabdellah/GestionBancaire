package RIM.Banque.GestionBancaire.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIM.Banque.GestionBancaire.entity.Role;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.RoleRepository;
import RIM.Banque.GestionBancaire.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	// @Autowired
	// private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(User user) {
//	public User registerNewUser(User user, boolean isAdmin) {
//		
//		if(isAdmin) {
//			Role role = roleRepository.findByName("Admin").get();
//			Set<Role> roles = new HashSet<>();
//			roles.add(role);
//			user.setRoles(roles);
//		}else {
//			Role role = roleRepository.findByName("Employee").get();
//			Set<Role> roles = new HashSet<>();
//			roles.add(role);
//			user.setRoles(roles);
//		}
		Role role = roleRepository.findByName("Employee").get();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(user.getPassword());
		return userRepository.save(user);
	}
	
	public void initRolesAndUser() {
		Role adminRole = new Role();
		adminRole.setName("Admin");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setName("Employee");
		roleRepository.save(userRole);
		
		
		User adminUser = new User();
		adminUser.setUsername("Mohamed Abdellahi");
		adminUser.setEmail("mhdabdellahi0@gmail.com");
		
		adminUser.setPassword("sidi1212");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		userRepository.save(adminUser);
		
		User employeeUser = new User();
		employeeUser.setUsername("Abdellahi");
		employeeUser.setEmail("mhdabdellahi2018@gmail.com");
		employeeUser.setPassword("sidi1212");
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		employeeUser.setRoles(userRoles);
		userRepository.save(employeeUser);
	}
	
	// @Bean

	// public String getEncodedPassword(String password) {
	// 	return passwordEncoder.encode(password);
		
	// }

}
