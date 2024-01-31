package AlmaLibre.eCommerce.controllers;
import AlmaLibre.eCommerce.dtos.ProductDTO;
import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.respositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @GetMapping("/products/{id}")
    public Product getProduct (@PathVariable Long id){
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }


    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDTO){

        if(productDTO.getName().isBlank()){
            return new ResponseEntity<>("Ingrese un nombre valido", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getDescription().isBlank()){
            return new ResponseEntity<>("Ingrese una descripcion valida", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getPrice()<= 0){
            return new ResponseEntity<>("Ingrese un precio valido", HttpStatus.FORBIDDEN);
        }
        if(productDTO.getStock() <= 0){
            return new ResponseEntity<>("Ingrese un stock valido", HttpStatus.FORBIDDEN);
        }
        else{
            Product newProduct = new Product(productDTO.getName(),productDTO.getPrice(),productDTO.getDescription(),productDTO.getProductType(),productDTO.getImg());
            newProduct.setCategory(productDTO.getCategory());
            if(productDTO.isFeatured() == true){
                List<Product> allProducts = productRepository.findAll();
                for(Product product : allProducts){
                    product.setFeatured(false);
                    productRepository.save(product);
                }
            }
            newProduct.setFeatured(productDTO.isFeatured());
            newProduct.setStock(productDTO.getStock());
            productRepository.save(newProduct);
            return new ResponseEntity<>("Producto creado Correct+amente",HttpStatus.CREATED);
        }
    }
    @GetMapping("/featured")
    public ResponseEntity<Object> getFeaturedProduct(){
        Optional<Product> featuredProduct = productRepository.findAll()
                .stream()
                .filter(product -> product.isFeatured()==true)
                .findFirst();
        if(featuredProduct.isPresent()){
            return new ResponseEntity<>(featuredProduct.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No featured product found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/products/delete")
    public ResponseEntity<Object> deleteProduct(@RequestParam Long id){
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            product.setDeleted(true);
            productRepository.save(product);
            return new ResponseEntity<>("Producto eliminado correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al eliminar el producto",HttpStatus.FORBIDDEN);
        }
    }
    @PatchMapping("/products/edit")
    public ResponseEntity<Object> editProduct(@RequestParam Long id,
                                              @RequestBody ProductDTO productDTO){
        Product product = productRepository.findById(id).orElse(null);
        if(productDTO.getProductType() != null){
            product.setProductType(productDTO.getProductType());
        }
        if(productDTO.getImg()!=null){
            if(product.getImgPrincipal() == null){
                product.setImgPrincipal(productDTO.getImg());
            }else{
                product.getOtherImages().add(productDTO.getImg());
            }
        }
        if(productDTO.getName()!=null){
            product.setName(productDTO.getName());
        }
        if(productDTO.getDescription() !=null){
            product.setDescription(productDTO.getDescription());
        }
        if(productDTO.getStock()>0){
            product.setStock(productDTO.getStock());
        }
        if(productDTO.getPrice()>0){
            product.setPrice(productDTO.getPrice());
        }

        productRepository.save(product);
        return new ResponseEntity<>("Producto modificado correctamente",HttpStatus.OK);
    }

    //FAVORITOS
    @PostMapping("/products/fav")
    public ResponseEntity<Object> setFav(@RequestParam Long id){
        Product product = productRepository.findById(id).orElse(null);
        product.setFav(true);
        productRepository.save(product);
        return new ResponseEntity<>("Agreagado a Favoritos ", HttpStatus.OK);
    }
    @PostMapping("/products/fav/delete")
    public ResponseEntity<Object> unsetFav(@RequestParam Long id){
        Product product = productRepository.findById(id).orElse(null);
        product.setFav(false);
        productRepository.save(product);
        return new ResponseEntity<>("Quitado de Favoritos ", HttpStatus.OK);
}

}
