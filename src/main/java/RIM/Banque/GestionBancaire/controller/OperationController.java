package RIM.Banque.GestionBancaire.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RIM.Banque.GestionBancaire.dto.comptes.OperationOnComptDto;
import RIM.Banque.GestionBancaire.dto.comptes.VirementDto;
import RIM.Banque.GestionBancaire.entity.Compte;
import RIM.Banque.GestionBancaire.entity.Operation;
import RIM.Banque.GestionBancaire.entity.TypeOperation;
import RIM.Banque.GestionBancaire.service.CompteService;
import RIM.Banque.GestionBancaire.service.OperationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/operations/")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private CompteService compteService;

    @GetMapping({ "getAllOperations" })
    // @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Operation>> getAllOperations() {
        // return "This URL Is only accessible to Admin";
        return ResponseEntity.ok().body(operationService.getAllOperations());
    }

    @GetMapping({ "getOperationById" })
    // @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Operation> getOperationById(@RequestBody Long operationid) {
        // return "This URL Is only accessible to Admin";
        return ResponseEntity.ok().body(operationService.getOperationById(operationid));
    }

    @GetMapping({ "getOperationBynumero" })
    // @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Operation> getOperationBynumero(@RequestBody String numeroOperation) {
        // return "This URL Is only accessible to Admin";
        return ResponseEntity.ok().body(operationService.getOperationBynumero(numeroOperation));
    }

    @GetMapping({ "getOperationsByCompte" })
    // @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Operation>> getOperationsByCompte(@RequestBody Long codeCompte) {
        // return "This URL Is only accessible to Admin";
        return ResponseEntity.ok().body(operationService.getOperationsByCompte(codeCompte));
    }

    @PostMapping("virementMontant") // codeCompte montant date => historique VersementDto
    public Object VirementMontant(@RequestBody VirementDto virementDto) {
        Compte compteSource = compteService.getCompteByCode(virementDto.getCodeCompteSource());
        Compte compteDestination = compteService.getCompteByCode(virementDto.getCodeCompteDestination());

        double compteSourceSolde = compteSource.getSolde();
        double compteDestinationSolde = compteDestination.getSolde();
        double montant = virementDto.getMontant();
        String message;
        if (montant < compteSourceSolde) {
            double compteSourcefinalSold = compteSourceSolde - montant;
            compteSource.setSolde(compteSourcefinalSold);
            compteService.save(compteSource);
            Operation operation = new Operation();
            operation.setCompte(compteSource);
            operation.setMontant(montant);
            operation.setDateOperation(new Date());
            // operation.setNumeroOperation(message);
            operation.setType("virement");
            operation.setTypeOperation(TypeOperation.VIRMENT);
            operationService.save(operation);
            double compteDestinationfinalSold = compteDestinationSolde + montant;
            compteDestination.setSolde(compteDestinationfinalSold);
            compteService.save(compteDestination);

            message = "un virment de " + montant + "vers le client " + compteSource.getClient().getFirstName() + ' '
                    + compteSource.getClient().getLastName() + "a ete bien effectuée";
        } else {
            message = "vous n'avez pas ce montant dans votre Compte pour effectuer un virement";
        }

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PostMapping("/deposerMontant") // codeCompte montant date => historique VersementDto
    public Object deposerToCompte(@RequestBody OperationOnComptDto operationDto) {
        Compte compte = compteService.getCompteByCode(operationDto.getCodeCompte());
        double curentSolde = compte.getSolde();
        double montant = operationDto.getMontant();
        double finalSold = curentSolde + montant;
        compte.setSolde(finalSold);
        compteService.save(compte);
        Operation operation = new Operation();
        operation.setCompte(compte);
        operation.setMontant(montant);
        operation.setDateOperation(new Date());
        // operation.setNumeroOperation(message);
        operation.setType("depot");
        operation.setTypeOperation(TypeOperation.VERSEMENT);
        operationService.save(operation);
        return ResponseEntity.status(HttpStatus.OK).body("la compte est bien ajoute");
    }

    @PostMapping("retirerMontant") // codeCompte montant date => historique
    public Object retirerFromCompte(@RequestBody OperationOnComptDto operationDto) {
        Compte compte = compteService.getCompteByCode(operationDto.getCodeCompte());
        double curentSolde = compte.getSolde();
        double montant = operationDto.getMontant();
        String message;
        if (montant < curentSolde) {
            double finalSold = curentSolde - montant;
            compte.setSolde(finalSold);
            compteService.save(compte);
            Operation operation = new Operation();
            operation.setCompte(compte);
            operation.setMontant(montant);
            operation.setDateOperation(new Date());
            // operation.setNumeroOperation(message);
            operation.setType("retirait");
            operation.setTypeOperation(TypeOperation.RETRAIT);
            operationService.save(operation);
            message = "le montant" + montant + "a ete bien retirée";
        } else {
            message = "vous n'avez pas ce montant dans votre Compte";
        }

        // compteService.save(compte);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
