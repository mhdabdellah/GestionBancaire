package RIM.Banque.GestionBancaire.dto.comptes;

public class PaymentDto {

    private Long numeroCarte;

    private Long codeCompteDestination;
    private double montant;

    public PaymentDto() {
        super();
    }

    public PaymentDto(Long numeroCarte, Long codeCompteDestination, double montant) {
        this.numeroCarte = numeroCarte;
        this.codeCompteDestination = codeCompteDestination;
        this.montant = montant;
    }

    public Long getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(Long numeroCarte) {
        this.numeroCarte = numeroCarte;
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
