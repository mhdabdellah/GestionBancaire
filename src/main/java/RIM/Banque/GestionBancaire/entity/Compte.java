package RIM.Banque.GestionBancaire.entity;

import java.io.Serializable;
//import java.util.Collection;
import java.util.Date;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="compte")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE"
,discriminatorType=DiscriminatorType.STRING,length=2)
public class Compte implements Serializable{ //abstract caar on va faire soit un compte courant soit un compte epargne

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codeCompte;
	
	
	private Date dateCreation;
	
	
	private String etat="active" ;
	
	private double  solde=0d ;
	
	
	@ManyToOne
	@JoinColumn(name="CODE_CLI", nullable=false)
	private Client client ;	
	
	
//	@OneToMany(mappedBy="compte", fetch=FetchType.LAZY, cascade=CascadeType.ALL)     
//	private Collection<Operation> operations; 
	
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Compte(Long codeCompte, Date dateCreation,double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		
		this.solde = solde;
		this.client = client;
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


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


//	public Collection<Operation> getOperations() {
//		return operations;
//	}


//	public void setOperations(Collection<Operation> operations) {
//		this.operations = operations;
//	}
	
}