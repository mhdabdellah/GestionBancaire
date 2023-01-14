package RIM.Banque.GestionBancaire.service;

import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RIM.Banque.GestionBancaire.repository.CompteRepository;
import RIM.Banque.GestionBancaire.repository.OperationRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OperationService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private OperationRepository operationRepository;

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Operation getOperationById(Long operationid) {
        return operationRepository.findById(operationid).get();
    }

    public Operation getOperationBynumero(String numeroOperation) {
        return operationRepository.findByNumeroOperation(numeroOperation);
    }

    public List<Operation> getOperationsByCompte(Long codeCompte) {
        Compte compte = compteRepository.findByCodeCompte(codeCompte);
        return operationRepository.findByCompte(compte);
    }

}
