package AlmaLibre.eCommerce.controllers;

import AlmaLibre.eCommerce.models.BuyOrder;
import AlmaLibre.eCommerce.models.BuyOrderState;
import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.models.ProductOrder;
import AlmaLibre.eCommerce.respositories.BuyOrderRepository;
import AlmaLibre.eCommerce.respositories.ProductOrderRepository;
import AlmaLibre.eCommerce.respositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BuyOrderController {

    @Autowired
    BuyOrderRepository buyOrderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;


     int generarNumeroOrden() {
        // Generar un número aleatorio de 5 dígitos
        Random random = new Random();
        return random.nextInt(90000) + 10000;
    }

    @PostMapping("/order")
    public ResponseEntity<Object> createBuyOrder(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> listId = requestBody.get("listId");

        BuyOrder newBuyOrder = new BuyOrder(LocalDateTime.now(),generarNumeroOrden(),0.0);
        newBuyOrder.setState(BuyOrderState.PENDIENTE);
        buyOrderRepository.save(newBuyOrder);

        double totalAmount = 0.0;
        Map<Long, ProductOrder> productIdToOrderMap = new HashMap<>();
        for(Long productId : listId){
            Product product = productRepository.findById(productId).orElse(null);
            if(product != null){
                ProductOrder productOrder = productIdToOrderMap.get(productId);
                if(productOrder == null){
                    productOrder = new ProductOrder(1,product,product.getPrice());
                    productOrder.setBuyOrder(newBuyOrder);
                    productOrderRepository.save(productOrder);
                    newBuyOrder.getProductOrders().add(productOrder);
                    productIdToOrderMap.put(productId, productOrder);
                }else{
                    int newQuantity = productOrder.getQuantity() +1;
                    productOrder.setQuantity(newQuantity);
                }
                totalAmount += product.getPrice();
            }
        }
        newBuyOrder.setAmount(totalAmount);
        buyOrderRepository.save(newBuyOrder);
        return new ResponseEntity<>("Buy order correcta", HttpStatus.OK);


        /*if (listId.isEmpty()) {
            return new ResponseEntity<>("La lista de productos está vacía", HttpStatus.BAD_REQUEST);
        }
        else{
            for(Long id : listId){
                Product product = productRepository.findById(id).orElse(null);
                newBuyOrder.addProduct(product);
            }
            BuyOrder saveOrder = buyOrderRepository.save(newBuyOrder);
            System.out.println(saveOrder.getProductList());
c        }*/

    }
    @GetMapping("/orders")
    public List<BuyOrder> getBuyOrders(){
        List<BuyOrder> buyOrders = buyOrderRepository.findAll();
        return buyOrders;
    }
    @PostMapping("/orders/state")
    public ResponseEntity<Object> changeState(@RequestParam Long id){
        BuyOrder buyOrder = buyOrderRepository.findById(id).orElse(null);
        if(buyOrder.getState() == BuyOrderState.PENDIENTE){
            buyOrder.setState(BuyOrderState.FINALIZADO);
            buyOrderRepository.save(buyOrder);
            return new ResponseEntity<>("Estado cambiado a finalizado",HttpStatus.OK);
        }
        else{
            buyOrder.setState(BuyOrderState.PENDIENTE);
            buyOrderRepository.save(buyOrder);
            return new ResponseEntity<>("Estado cambiado a pendiente",HttpStatus.OK);
        }
    }

}
