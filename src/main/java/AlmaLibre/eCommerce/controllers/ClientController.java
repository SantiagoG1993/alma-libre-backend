package AlmaLibre.eCommerce.controllers;

import AlmaLibre.eCommerce.models.Client;
import AlmaLibre.eCommerce.respositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/clients")
    public ResponseEntity<Object> registerClient(@RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam String phoneNumber,
                                                 @RequestParam String password,
                                                 @RequestParam String email){
        if(clientRepository.findByEmail(email)!=null){
            return new ResponseEntity<>("el email ya se encuentra regitrado",HttpStatus.FORBIDDEN);
        }

        if(firstName.isBlank() == true){
            return new ResponseEntity<>("Ingrese el nombre", HttpStatus.FORBIDDEN);
        }
        if(lastName.isBlank() == true){
            return new ResponseEntity<>("Ingrese el apellido", HttpStatus.FORBIDDEN);
        }
        if(phoneNumber.isBlank() == true){
            return new ResponseEntity<>("Ingrese un numero de telefono", HttpStatus.FORBIDDEN);
        }
        if(password.isBlank() == true){
            return new ResponseEntity<>("Ingrese una contrasenia", HttpStatus.FORBIDDEN);
        }
        if(email.isBlank() == true){
            return new ResponseEntity<>("ingrese un correo electronico",HttpStatus.FORBIDDEN);
        }
        else{
            Client newClient = new Client(firstName,lastName,phoneNumber,password,email);
            clientRepository.save(newClient);

            return new ResponseEntity<>("Usuario creado correctamente", HttpStatus.CREATED);
        }


    }


}
