package Entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="Product.searchAll", query="select o from Product o where o.active = true"),
        @NamedQuery(name="Product.searchByID", query="select o from Product o where o.productID = :productid"),
        @NamedQuery(name="Product.searchByTitle", query="select o from Product o where o.active = true and o.title like CONCAT('%', :title, '%')"),
        @NamedQuery(name="Product.searchByManufacturer", query="select o from Product o where o.active = true and o.manufacturer = :manufacturer"),
        @NamedQuery(name="Product.searchByCategory", query="select o from Product o where o.active = true and o.category like CONCAT('%', :category, '%')"),
})

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    private String title, manufacturer, category;
    private Double price;
    private String imgName; //might not need???
    private boolean active;

    public Product(String title, String manufacturer, String category, Double price/*, String imgName*/) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
        this.active = true;
//        this.imgName = imgName;
    }

    public Product() {}

    //getters and setters

    public int getProductID() {
        return productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
