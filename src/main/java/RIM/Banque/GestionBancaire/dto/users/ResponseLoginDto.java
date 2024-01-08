package RIM.Banque.GestionBancaire.dto.users;

import java.util.HashSet;
import java.util.Set;

import RIM.Banque.GestionBancaire.entity.Role;

public class ResponseLoginDto {

	// private String firstName;
	// private String lastName;
	private String username;
	private String email;
	private String token;
	private Long id;
	private Set<Role> roles = new HashSet<>();

	public ResponseLoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseLoginDto(String username, String email, String token, Long id, Set<Role> roles) {
		super();
		this.username = username;
		this.email = email;
		this.token = token;
		this.id = id;
		this.roles = roles;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
