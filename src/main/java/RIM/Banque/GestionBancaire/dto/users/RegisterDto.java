package RIM.Banque.GestionBancaire.dto.users;

public class RegisterDto {


//	Register example for test in post man

//	{
//			"firstName":"Mohamedou",
//			"lastName":"fi",
//			"username":"Muhamedoufi",
//			"email":"muhamedoufi@gmail.com",
//			"phone":"32335832",
//			"password":"123456",
//			"isAdmin":"false"
//	}
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String phone;
	private String password;
	private boolean isAdmin;

	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterDto(String firstName, String lastName, String username, String email, String phone,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public RegisterDto(String firstName, String lastName, String username, String email, String phone, String password,
			boolean isAdmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
