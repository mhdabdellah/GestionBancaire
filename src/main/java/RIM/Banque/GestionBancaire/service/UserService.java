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
import RIM.Banque.GestionBancaire.dto.users.RegisterDto;
import RIM.Banque.GestionBancaire.dto.users.ResponseLoginDto;
import RIM.Banque.GestionBancaire.entity.Contact;
import RIM.Banque.GestionBancaire.entity.Role;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.ContactRepository;
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
	private ContactRepository contactRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User registerNewUser(RegisterDto registerDto) {
		Set<Role> roles = new HashSet<>();
		if (registerDto.getIsAdmin()) {
			Role role = roleRepository.findByName("Admin").get();
			roles.add(role);
		} else {
			Role role = roleRepository.findByName("Client").get();
			roles.add(role);
		}
		User user = new User();

		Set<Contact> contacts = new HashSet<>();
		Contact contact = new Contact();
		contact.setEmail(registerDto.getEmail());
		contact.setTelephone(registerDto.getPhone());

		contacts.add(contact);
		user.setUsername(registerDto.getUsername());
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		user.setContacts(contacts);
		user.setRoles(roles);
		user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
		contact.setClient(user);
		contactRepository.save(contact);
		return userRepository.save(user);
	}

	public void initRolesAndUser() {
		Role admin = new Role();
		admin.setName("Admin");
		roleRepository.save(admin);

		Role client = new Role();
		client.setName("Client");
		roleRepository.save(client);

		Contact mohamedContact1 = new Contact();
		mohamedContact1.setEmail("mohamed1@gmail.com");
		mohamedContact1.setTelephone("26262626");
		contactRepository.save(mohamedContact1);

		Contact mohamedContact2 = new Contact();
		mohamedContact2.setEmail("mohamed2@gmail.com");
		mohamedContact2.setTelephone("22262626");
		contactRepository.save(mohamedContact2);

		Contact abdellahiContact2 = new Contact();
		abdellahiContact2.setEmail("abdellahi2@gmail.com");
		abdellahiContact2.setTelephone("28262626");
		contactRepository.save(abdellahiContact2);

		Contact abdellahiContact1 = new Contact();
		mohamedContact1.setEmail("abdellahi1@gmail.com");
		mohamedContact1.setTelephone("27262626");
		contactRepository.save(abdellahiContact1);

		User adminUser = new User();
		adminUser.setUsername("Mohamed");

		adminUser.setPassword(bCryptPasswordEncoder.encode("sidi1212"));
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(admin);
		adminUser.setRoles(adminRoles);
		Set<Contact> mohamedContacts = new HashSet<>();
		mohamedContacts.add(mohamedContact1);
		mohamedContacts.add(mohamedContact2);
		adminUser.setContacts(mohamedContacts);
		userRepository.save(adminUser);

		User employeeUser = new User();
		employeeUser.setUsername("Abdellahi");
		employeeUser.setPassword(bCryptPasswordEncoder.encode("sidi1212"));
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(client);
		employeeUser.setRoles(userRoles);
		Set<Contact> abdellahiContacts = new HashSet<>();
		abdellahiContacts.add(abdellahiContact1);
		abdellahiContacts.add(abdellahiContact2);
		employeeUser.setContacts(abdellahiContacts);
		userRepository.save(employeeUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid UserName Or Password");
		} else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					mapToGrantedAuthorities(user.getRoles()));
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
		UserDetails userDetails = loadUserByUsername(loginUser.getUsername());
		User user = userRepository.findByUsername(loginUser.getUsername());
		if (userDetails == null) {
			throw new UsernameNotFoundException("Invalid UserName Or Password");
		} else {
			String authString = loginUser.getUsername() + ":" + loginUser.getPassword();
			byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
			String basicAuthToken = "Basic " + new String(authEncBytes);

			ResponseLoginDto responseAfterLogin = new ResponseLoginDto();
			responseAfterLogin.setUsername(user.getUsername());
			// List<Contact> contact = user.getContacts();
			// responseAfterLogin.setEmail(user.getEmail());
			responseAfterLogin.setToken(basicAuthToken);
			responseAfterLogin.setId(user.getId());
			responseAfterLogin.setRoles(user.getRoles());
			// responseAfterLogin.setRoles(use);
			// httpRequest.setHeader("Authorization", basicAuth);
			return responseAfterLogin;

		}

	}

	public boolean deletUser(Long userid) {
		userRepository.deleteById(userid);
		boolean result = true;
		return result;
	}

	public User getUserById(Long userid) {
		return userRepository.findById(userid).get();
	}

	public User getUserByName(String userName) {
		return userRepository.findByUsername(userName);
	}

	// @Bean
	// public String getEncodedPassword(String password) {
	// return bCryptPasswordEncoder.encode(password);

	// }

}
