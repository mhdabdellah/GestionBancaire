package RIM.Banque.GestionBancaire.dto.comptes;

public class VirementDto {
	private Long codeCompteSource;
	private Long codeCompteDestination;
	private double montant ;
	public VirementDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VirementDto(Long codeCompteSource, Long codeCompteDestination, double montant) {
		super();
		this.codeCompteSource = codeCompteSource;
		this.codeCompteDestination = codeCompteDestination;
		this.montant = montant;
	}
	public Long getCodeCompteSource() {
		return codeCompteSource;
	}
	public void setCodeCompteSource(Long codeCompteSource) {
		this.codeCompteSource = codeCompteSource;
	}
	public Long getCodeCompteDestination() {
		return codeCompteDestination;
	}
	public void setCodeCompteDestination(Long codeCompteDestination) {
		this.codeCompteDestination = codeCompteDestination;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
	
}
