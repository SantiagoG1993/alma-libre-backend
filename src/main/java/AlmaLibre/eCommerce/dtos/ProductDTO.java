package AlmaLibre.eCommerce.dtos;

import AlmaLibre.eCommerce.models.Product;
import AlmaLibre.eCommerce.models.ProductType;
import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private Long id;
    private String description, name,img,category;
    private double price;
    private int stock;
    private boolean featured;
    private ProductType productType;
    private Boolean fav;
    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        description = product.getDescription();
        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();
        featured = product.isFeatured();
        productType = product.getProductType();
        id = product.getId();
    }

    public Boolean getFav() {
        return fav;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }
}
