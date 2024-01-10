package AlmaLibre.eCommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BuyOrderState state;
    private LocalDateTime date;
    private int orderNumber;
    private double amount;

    public BuyOrder(LocalDateTime date, int orderNumber, double amount) {
        this.date = date;
        this.orderNumber = orderNumber;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
    private List<Product> productList = new ArrayList<>();

    public BuyOrder(LocalDateTime date,int orderNumber) {
        this.date = date;
        this.orderNumber = orderNumber;
    }
    @OneToMany(mappedBy = "buyOrder")
    @JsonManagedReference
    private Set<ProductOrder> productOrders = new HashSet<>();

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

    public void addProduct(Product product){
        this.productList.add(product);
        product.setOrder(this);
    }

    public BuyOrderState getState() {
        return state;
    }

    public void setState(BuyOrderState state) {
        this.state = state;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Set<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    @Override
    public String toString() {
        return "BuyOrder{" +
                "productList=" + productList +
                '}';
    }
}
