package RIM.Banque.GestionBancaire.entity;

import java.util.HashSet;
import java.util.Set;

// import jakar.validation.constraints.*;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;

import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
// @UniqueConstraint(columnNames = "email")
})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @NotBlank
  // @Size(max = 20)
  @Column(length = 20)
  private String username;

  // @NotBlank
  // @Size(max = 50)
  // @Email
  @Column(length = 30)
  private String firstName;

  @Column(length = 30)
  private String lastName;

  // @NotBlank
  // @Size(max = 120)
  @Column(length = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  // @JoinTable(name = "users_roles", joinColumns = {
  // @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
  // updatable = false) }, inverseJoinColumns = {
  // @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false,
  // updatable = false) })
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Contact> contacts = new HashSet<>();

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Compte> comptes = new HashSet<>();

  public User() {
  }

  public User(Long id, String username, String firstName, String lastName, String password, Set<Role> roles,
      Set<Contact> contacts, Set<Compte> comptes) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.roles = roles;
    this.contacts = contacts;
    this.comptes = comptes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  // public String getEmail() {
  // return email;
  // }

  // public void setEmail(String email) {
  // this.email = email;
  // }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Set<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(Set<Contact> contacts) {
    this.contacts = contacts;
  }

  public Set<Compte> getComptes() {
    return comptes;
  }

  public void setComptes(Set<Compte> comptes) {
    this.comptes = comptes;
  }

}
