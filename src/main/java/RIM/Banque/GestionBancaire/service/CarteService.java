package RIM.Banque.GestionBancaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RIM.Banque.GestionBancaire.entity.Carte;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.repository.CarteRepository;

@Service
public class CarteService {
    @Autowired
    CarteRepository carteRepository;

    public Carte generateCarte(Carte carte) {
        return carteRepository.save(carte);
    }

    public boolean deleteCarte(Carte carte) {
        carteRepository.delete(carte);
        return true;
    }

    public Carte updateCarte(Carte carte) {
        return carteRepository.save(carte);
    }

    public Carte getCarteByNumero(long numero) {
        return carteRepository.findByNumero(numero).get();
    }

    public Carte getCarteByCode(String code) {
        return carteRepository.findByCode(code).get();
    }

    public List<Carte> getCarteByCompte(Compte compte) {
        return carteRepository.findCarteByCompte(compte);
    }

    public List<Carte> getAllCartes() {
        return carteRepository.findAll();
    }

}
