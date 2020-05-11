package Entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="Order.searchAll", query="select o from Orders o"),
        @NamedQuery(name="Order.searchByID", query="select o from Orders o where o.orderID = :orderid"),
        @NamedQuery(name="Order.searchByDate", query="select o from Orders o where o.date = :date"),
        @NamedQuery(name="Order.searchByCustomer", query="select o from Orders o where o.customerID = :customerid"),
//        @NamedQuery(name="Order.searchByProduct", query="select o from Order o where o.manufacturer = :manufacturer"),
})

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    private String customerID, date;
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Product> products;

    public Orders(String date, Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
        this.customerID = "" + customer.getCustomerID();
        this.date = date;
        this.totalPrice = getTotalPrice();
    }

    public Orders() {}

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        double total = 0.0;
        if(!products.isEmpty()) {
            for(Product p: products) {
                total = total + p.getPrice();
            }
        }
        return total;
    }

    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerID() {
        return customerID;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
