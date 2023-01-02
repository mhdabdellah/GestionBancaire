package RIM.Banque.GestionBancaire.service;

import RIM.Banque.GestionBancaire.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RIM.Banque.GestionBancaire.repository.OperationRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OperationService {

    @Autowired
    OperationRepository operationRepository;

    public Operation save(Operation operation){
        return operationRepository.save(operation);
    }

}
