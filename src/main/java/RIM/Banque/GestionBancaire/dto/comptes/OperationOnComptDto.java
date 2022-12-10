package RIM.Banque.GestionBancaire.dto.comptes;

public class OperationOnComptDto {
	private Long codeCompte;
	private double montant ;
	public OperationOnComptDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OperationOnComptDto(Long codeCompte, double montant) {
		super();
		this.codeCompte = codeCompte;
		this.montant = montant;
	}
	public Long getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(Long codeCompte) {
		this.codeCompte = codeCompte;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
}
