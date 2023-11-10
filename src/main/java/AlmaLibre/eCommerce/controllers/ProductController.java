package AlmaLibre.eCommerce.controllers;

import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.models.ProductType;
import AlmaLibre.eCommerce.respositories.ProductRepository;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> products(){
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @GetMapping("/products/{id}")
    public Product getProduct (@PathVariable Long id){
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestParam String name,
                                                @RequestParam String description,
                                                @RequestParam String img,
                                                @RequestParam int price,
                                                @RequestParam boolean isFeatured,
                                                @RequestParam int stock,
                                                @RequestParam ProductType productType){

        if(name.isBlank()){
            return new ResponseEntity<>("Ingrese un nombre valido", HttpStatus.FORBIDDEN);
        }
        if(description.isBlank()){
            return new ResponseEntity<>("Ingrese una descripcion valida", HttpStatus.FORBIDDEN);
        }
        if(img.isBlank()){
            return new ResponseEntity<>("Ingrese una url valida", HttpStatus.FORBIDDEN);
        }
        if(price<= 0){
            return new ResponseEntity<>("Ingrese un precio valido", HttpStatus.FORBIDDEN);
        }
        if(stock <= 0){
            return new ResponseEntity<>("Ingrese un stock valido", HttpStatus.FORBIDDEN);
        }
        else{
            Product newProduct = new Product(name,price,img,description,productType);
            newProduct.setFeatured(isFeatured);
            newProduct.setStock(stock);
            productRepository.save(newProduct);
            return new ResponseEntity<>("Producto creado Correctamente",HttpStatus.CREATED);
        }
    }
}
