package RIM.Banque.GestionBancaire.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIM.Banque.GestionBancaire.dto.users.LoginDto;
import RIM.Banque.GestionBancaire.dto.users.ResponseLoginDto;
import RIM.Banque.GestionBancaire.entity.Role;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.RoleRepository;
import RIM.Banque.GestionBancaire.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		// Role role = roleRepository.findByName("Employee").get();
		Set<Role> roles = new HashSet<>();
		// roles.add(role);
		user.setRoles(roles);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
		adminUser.setUsername("Mohamed");
		adminUser.setEmail("mhdabdellahi0@gmail.com");
		
		adminUser.setPassword(bCryptPasswordEncoder.encode("sidi1212"));
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		userRepository.save(adminUser);
		
		User employeeUser = new User();
		employeeUser.setUsername("Abdellahi");
		employeeUser.setEmail("mhdabdellahi2018@gmail.com");
		employeeUser.setPassword(bCryptPasswordEncoder.encode("sidi1212"));
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		employeeUser.setRoles(userRoles);
		userRepository.save(employeeUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid UserName Or Password");
		}else{
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapToGrantedAuthorities(user.getRoles()));
		}
	}

	private Collection<? extends GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles) {
		ArrayList<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		return grantedAuthoritiesList;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

    public ResponseLoginDto login(LoginDto loginUser) {
		String authString = loginUser.getUsername() + ":" + loginUser.getPassword();
		byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
		String basicAuthToken = "Basic " + new String(authEncBytes);

		ResponseLoginDto responseAfterLogin = new ResponseLoginDto();
		responseAfterLogin.setUsername(loginUser.getUsername());
		responseAfterLogin.setEmail(basicAuthToken);
		responseAfterLogin.setToken(basicAuthToken);
		// httpRequest.setHeader("Authorization", basicAuth);
        return responseAfterLogin;
    }
	
	// @Bean
	// public String getEncodedPassword(String password) {
	// 	return bCryptPasswordEncoder.encode(password);
		
	// }

}
