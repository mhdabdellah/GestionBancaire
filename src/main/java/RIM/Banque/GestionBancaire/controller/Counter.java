package RIM.Banque.GestionBancaire.controller;


import RIM.Banque.GestionBancaire.repository.CompteRepository;
import RIM.Banque.GestionBancaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/counter")
public class Counter {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public Map<String, Double> count() {
        HashMap<String, Double> map = new HashMap<>();

        map.put("Total comptes", (double) compteRepository.count());
        map.put("Total utilisateur", (double) userRepository.count());

//		map.put("aa", "bb");
        return map;
    }
}
