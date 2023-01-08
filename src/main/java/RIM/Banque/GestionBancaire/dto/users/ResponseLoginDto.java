package RIM.Banque.GestionBancaire.dto.users;

import RIM.Banque.GestionBancaire.entity.Role;

import java.util.Collection;

public class ResponseLoginDto {

	// private String firstName;
	// private String lastName;
	private String username;
	private String email;
	private String token;
	private Collection<Role> roles;

	public ResponseLoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public ResponseLoginDto(String username, String email, String token, Collection roles) {
		super();
		this.username = username;
		this.email = email;
		this.token = token;
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
