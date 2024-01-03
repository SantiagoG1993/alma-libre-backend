package AlmaLibre.eCommerce.controllers;

import AlmaLibre.eCommerce.models.BuyOrder;
import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.respositories.BuyOrderRepository;
import AlmaLibre.eCommerce.respositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BuyOrderController {

    @Autowired
    BuyOrderRepository buyOrderRepository;
    @Autowired
    ProductRepository productRepository;


    @PostMapping("/order")
    public ResponseEntity<Object> createBuyOrder(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> listId = requestBody.get("listId");
        BuyOrder newBuyOrder = new BuyOrder();
        if (listId.isEmpty()) {
            return new ResponseEntity<>("La lista de productos está vacía", HttpStatus.BAD_REQUEST);
        }
        else{
            for(Long id : listId){
                Product product = productRepository.findById(id).orElse(null);
                int quantity = Collections.frequency(listId, id);
                newBuyOrder.addProduct(product,quantity);
            }
            BuyOrder saveOrder = buyOrderRepository.save(newBuyOrder);
            return new ResponseEntity<>("Orden de compra creada correctamente"+saveOrder.getId(),HttpStatus.CREATED);
        }
    }
    @GetMapping("/orders")
    public List<BuyOrder> getBuyOrders(){
        List<BuyOrder> buyOrders = buyOrderRepository.findAll();
        return buyOrders;
    }

}
