package RIM.Banque.GestionBancaire.dto.comptes;

import RIM.Banque.GestionBancaire.entity.StatuesCompte;
import RIM.Banque.GestionBancaire.entity.User;

import java.util.Date;

public class CompteDTO {
    private Date dateCreation;
    private StatuesCompte StatuesCompte;
//    private double solde;
    private User client;

    public CompteDTO() {
        super();
    }

    public CompteDTO(Date dateCreation,User client,StatuesCompte statuesCompte) {
        this.dateCreation = dateCreation;
        this.client = client;
        this.StatuesCompte = statuesCompte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public StatuesCompte getStatuesCompte() {
        return StatuesCompte;
    }

    public void setStatuesCompte(RIM.Banque.GestionBancaire.entity.StatuesCompte statuesCompte) {
        StatuesCompte = statuesCompte;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
