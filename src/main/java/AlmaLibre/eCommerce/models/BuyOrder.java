package AlmaLibre.eCommerce.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
    private List<Product> productList = new ArrayList<>();


    public BuyOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product,int quantity){
        for (int i = 0; i < quantity; i++) {
            this.productList.add(product);
        }
        product.setOrder(this);
    }
}
