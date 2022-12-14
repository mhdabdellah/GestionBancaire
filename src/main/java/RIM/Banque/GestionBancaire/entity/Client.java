package RIM.Banque.GestionBancaire.entity;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="client")
public class Client implements Serializable{
     
     
	@Id
	private Long code;
  
	private String nom;
	private String prenom;
	private String date_nais;
	private String civilite;
	private String adresse;
	private String tel;
	private String email;
	 
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY )
	//car c'est une association
	private Collection<Compte> comptes; 
	
	 
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(Long code,String nom, String prenom, String date_nais, String civilite, String adresse, String tel, String email) {
		this.code=code; 
		this.nom = nom;
		this.prenom = prenom;
		this.date_nais = date_nais;
		this.civilite = civilite;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}   


	

	public Long getCode() {
		return code;
	}


	public void setCode(Long code) {
		this.code = code;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getDate_nais() {
		return date_nais;
	}


	public void setDate_nais(String date_nais) {
		this.date_nais = date_nais;
	}


	public String getCivilite() {
		return civilite;
	}


	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Collection<Compte> getComptes() {
		return comptes;
	}


	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	 	
}