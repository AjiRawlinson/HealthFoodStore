package Entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="Customer.findAll", query="select o from Customer o where o.active = true"),
        @NamedQuery(name="Customer.findByName", query="select o from Customer o where o.active = true and o.userName = :userName"),
        @NamedQuery(name="Customer.findByID", query="select o from Customer o where o.customerID = :customerID"),
        @NamedQuery(name="Customer.findPasswordByID", query="select o.password from Customer o where o.customerID = :customerID")
})

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    private String userName, password, address, cardNumber;
    private boolean active;
    //private String email;???

    public Customer(String userName, String password, String address, String cardNumber) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.cardNumber = cardNumber;
        this.active = true;
    }
    public Customer(){}

    public int getCustomerID() {
        return customerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
