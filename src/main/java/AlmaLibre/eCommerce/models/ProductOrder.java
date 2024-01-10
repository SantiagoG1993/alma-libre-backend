package AlmaLibre.eCommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="buy_order_id")
    @JsonBackReference
    private BuyOrder buyOrder;
    private double amount;

    public ProductOrder() {
    }

    public ProductOrder(int quantity, Product product, double amount) {
        this.quantity = quantity;
        this.product = product;
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BuyOrder getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(BuyOrder buyOrder) {
        this.buyOrder = buyOrder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "quantity=" + quantity +
                ", product=" + product +
                ", buyOrder=" + buyOrder +
                ", amount=" + amount +
                '}';
    }
}
