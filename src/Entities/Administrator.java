package Entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="Administrator.findAll", query="select o from Administrator o"),
        @NamedQuery(name="Administrator.findByName", query="select o from Administrator o where o.userName = :userName"),
        @NamedQuery(name="Administrator.findByID", query="select o from Administrator o where o.adminID = :adminID"),
        @NamedQuery(name="Administrator.findPasswordByID", query="select o.password from Administrator o where o.adminID = :adminID")
})

@Entity
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminID;
    private String userName, password;

    public Administrator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }



    public Administrator() {}

    public int getAdminID() {
        return adminID;
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
}
