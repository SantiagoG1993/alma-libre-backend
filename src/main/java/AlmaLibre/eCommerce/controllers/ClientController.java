package AlmaLibre.eCommerce.controllers;

import AlmaLibre.eCommerce.models.Client;
import AlmaLibre.eCommerce.respositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;


    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientRepository.findAll();
    }
    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable Long id){
        return clientRepository.findById(id).orElse(null);
    }


}
