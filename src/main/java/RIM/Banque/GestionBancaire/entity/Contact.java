package RIM.Banque.GestionBancaire.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String email;

    @Column(length = 60)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;

    public Contact() {
        super();
    }

    public Contact(Long id, String email, String telephone, User client) {
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @JsonBackReference
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

}
