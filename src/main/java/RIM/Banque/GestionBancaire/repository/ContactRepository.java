package RIM.Banque.GestionBancaire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import RIM.Banque.GestionBancaire.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByTelephone(String telphone);

    Optional<Contact> findByEmail(String email);

}
