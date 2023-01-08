package RIM.Banque.GestionBancaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIM.Banque.GestionBancaire.dto.users.LoginDto;
import RIM.Banque.GestionBancaire.dto.users.RegisterDto;
import RIM.Banque.GestionBancaire.dto.users.ResponseLoginDto;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.service.UserService;
import jakarta.annotation.PostConstruct;

@CrossOrigin("*")
@RestController

@RequestMapping("/users/")

public class UserController {

	@Autowired
	private UserService userService;

	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRolesAndUser();
	}

	@PostMapping("registerNewUser")
	public ResponseEntity<User> registerUser(@RequestBody RegisterDto registerDto) {
		// return userService.registerNewUser(user);
		return ResponseEntity.ok().body(userService.registerNewUser(registerDto));
	}

	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody LoginDto loginUser) {
		// return userService.registerNewUser(user);
		ResponseLoginDto responseAfterLogin = userService.login(loginUser);
		System.out.println(responseAfterLogin.getUsername());
		System.out.println(responseAfterLogin.getEmail());
		System.out.println(responseAfterLogin.getToken());

		return ResponseEntity.ok().body(responseAfterLogin);
	}

	@GetMapping({ "getAllUsers" })
	// @PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<User>> getAllUsers() {
		// return "This URL Is only accessible to Admin";
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	@GetMapping({ "forAdmin" })
	// @PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This URL Is only accessible to Admin";
	}

	@GetMapping({ "forEmplyee" })
	// @PreAuthorize("hasRole('Employee')")
	public String forEmployee() {
		return "This URL is only accessible to Employee";
	}

	@GetMapping({ "forAllUsers" })
	// @PreAuthorize("hasAnyRole('Admin','Employee')")
	public String forAllUsers() {
		return "This URL is accessible to All Users of our System ";
	}
}
