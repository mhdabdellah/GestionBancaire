package RIM.Banque.GestionBancaire.entity;

import java.io.Serializable;
//import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public class Compte implements Serializable { // abstract caar on va faire soit un compte courant soit un compte epargne

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codeCompte;

	private Date dateCreation;

	private String etat = "active";

	private double solde = 0d;

	// @ManyToOne
	// @JoinColumn(name="CODE_CLI", nullable=false)
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User client;

	// @OneToMany(mappedBy="compte", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	// private Collection<Operation> operations;

	// @OneToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "compte_cartes", joinColumns = @JoinColumn(name =
	// "compte_id"), inverseJoinColumns = @JoinColumn(name = "carte_id"))
	@OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Carte> cartes = new HashSet<>();

	@OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Operation> operations = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private StatuesCompte statuesCompte;

	public Compte() {
		super();
	}

	public Compte(Long codeCompte, Date dateCreation, String etat, double solde, User client, Set<Carte> cartes,
			Set<Operation> operations, StatuesCompte statuesCompte) {
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.etat = etat;
		this.solde = solde;
		this.client = client;
		this.cartes = cartes;
		this.operations = operations;
		this.statuesCompte = statuesCompte;
	}

	public Long getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(Long codeCompte) {
		this.codeCompte = codeCompte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(Set<Carte> cartes) {
		this.cartes = cartes;
	}

	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	public StatuesCompte getStatuesCompte() {
		return statuesCompte;
	}

	public void setStatuesCompte(StatuesCompte statuesCompte) {
		this.statuesCompte = statuesCompte;
	}

}