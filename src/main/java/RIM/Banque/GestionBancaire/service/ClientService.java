package RIM.Banque.GestionBancaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import RIM.Banque.GestionBancaire.entity.Client;
import RIM.Banque.GestionBancaire.repository.ClientRepository;


@Service
@Transactional
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}
	
	public  List<Client> getClients() {
		return clientRepository.findAll();
	}

}
