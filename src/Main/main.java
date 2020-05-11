package Main;

import DAO.DataAccessObject;
import Entities.*;
import java.util.ArrayList;

public class main {

    public static void main(String[] heatedDiscussions) {
        System.out.println("testing 123");

        Administrator admin = new Administrator("Admin", "Admin");
        Customer cust = new Customer("Tony", "password123", "123 Main road", "1234123412341234");
        Product prod = new Product("Banana", "Banana Company", "Fruit", 0.0);

        ArrayList<Product> orderList = new ArrayList<Product>();
        orderList.add(prod);
        Orders order = new Orders("12-04-08", cust, orderList);

        DataAccessObject DAO = new DataAccessObject();
        DAO.persistAdmin(admin);
        DAO.persistCustomer(cust);
        DAO.persistProduct(prod);
        DAO.persistOrder(order);

        Administrator admin2 = DAO.getAdminByUsername("Admin");
        System.out.println("Admin get test: " + admin2.getPassword() + admin2.getUserName());
        ArrayList<Customer> custs = (ArrayList<Customer>) DAO.getAllCustomer();




        for(Customer c: custs) {
            System.out.println("" + c.getUserName());
//            DAO.removeCustomer(c);
        }

        DAO.removeOrder(order);
        DAO.removeCustomer(cust);

        ArrayList<Product> prods = (ArrayList<Product>) DAO.getAllProducts();
        for(Product p: prods) {
            System.out.println("" + p.getTitle());
//            DAO.removeProduct(p);
        }


    }


}
