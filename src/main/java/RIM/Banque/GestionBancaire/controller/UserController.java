package RIM.Banque.GestionBancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.service.UserService;
import jakarta.annotation.PostConstruct;



@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRolesAndUser();
	}
	
	@PostMapping({"/registerNewUser"})
	public User registerUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}
	
	@GetMapping({"/forAdmin"})
	// @PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This URL Is only accessible to Admin";
	}
	
	@GetMapping({"/forEmplyee"})
	// @PreAuthorize("hasRole('Employee')")
	public String forEmployee() {
		return "This URL is only accessible to Employee";
	}
	
	@GetMapping({"/forAllUsers"})
	// @PreAuthorize("hasAnyRole('Admin','Employee')")
	public String forAllUsers() {
		return "This URL is accessible to All Users of our System ";
	}
}
