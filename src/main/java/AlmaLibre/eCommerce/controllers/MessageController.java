package AlmaLibre.eCommerce.controllers;
import AlmaLibre.eCommerce.models.Message;
import AlmaLibre.eCommerce.respositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/messages")
    public List<Message> getMessages(){
        List<Message> messages = messageRepository.findAll();
        return messages;
    }
    @PostMapping("/messages")
    public ResponseEntity<Object> createMessage(@RequestParam String name,
                                                @RequestParam String email,
                                                @RequestParam String message){
        if(name.isBlank()){
            return new ResponseEntity<>("Ingrese su nombre", HttpStatus.FORBIDDEN);
        }
        if(email.isBlank()){
            return new ResponseEntity<>("Ingrese su correo electronico", HttpStatus.FORBIDDEN);
        }
        if(message.isBlank()){
            return new ResponseEntity<>("Ingrese un mensaje", HttpStatus.FORBIDDEN);
        }else{
            Message newMessage = new Message(name,email,message, LocalDateTime.now());
            messageRepository.save(newMessage);
            return new ResponseEntity<>("Su mensaje ha sido enviado correctamente", HttpStatus.CREATED);
        }
    }
    @PostMapping("/messages/delete")
    public ResponseEntity<Object> deleteMessage(@RequestParam Long id){
        Message message  = messageRepository.findById(id).orElse(null);
        if(message != null){
            message.setDeleted(true);
            messageRepository.save(message);
            return new ResponseEntity<>("Mensaje borrado correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al eliminar el mensaje", HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping("/messages/read")
    public ResponseEntity<Object> readMessage(@RequestParam Long id){
        Message message  = messageRepository.findById(id).orElse(null);
        if(message!=null){
            message.setRead(true);
            messageRepository.save(message);
            return new ResponseEntity<>("Mensaje marcado como leido",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al intentar leer msj",HttpStatus.FORBIDDEN);
        }
    }
}
