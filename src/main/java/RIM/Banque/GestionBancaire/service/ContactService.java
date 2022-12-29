package RIM.Banque.GestionBancaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RIM.Banque.GestionBancaire.entity.Contact;
import RIM.Banque.GestionBancaire.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact createNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public boolean deleteContact(Contact contact) {
        contactRepository.delete(contact);
        return true;
    }

    public Contact getContactByPhone(String telephone) {
        return contactRepository.findByTelephone(telephone).get();
    }

    public Contact getContactByEmail(String email) {
        return contactRepository.findByEmail(email).get();
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

}
