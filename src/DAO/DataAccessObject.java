package DAO;

import Entities.*;
import sun.dc.pr.PRError;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DataAccessObject {

    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("fred");

    public DataAccessObject() {/*nothing to see here, NADA, move on*/}

    public void persistAdmin(Administrator admin) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
        em.close();
    }

    public Administrator getAdminByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        List<Administrator> admins = (List<Administrator>) em.createNamedQuery("Administrator.findByName").setParameter("userName", username).getResultList();
        em.close();
        return admins.get(0);
    }

    public void persistCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    public void mergeCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }

    public List<Customer> getAllCustomer() {
        EntityManager em = emf.createEntityManager();
        List<Customer> custs = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        em.close();
        return custs;
    }

    public Customer getCustomerByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        List<Customer> custs = (List<Customer>) em.createNamedQuery("Customer.findByName").setParameter("userName", username).getResultList();
        em.close();
        return custs.get(0);
    }

    public void removeCustomer(Customer customer) {
        customer.setActive(false);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }

    public Customer getCustomerByID(int id) {
        EntityManager em = emf.createEntityManager();
        List<Customer> custs = (List<Customer>) em.createNamedQuery("Customer.findByID").setParameter("customerID", id).getResultList();
        em.close();
        return custs.get(0);
    }

    public void persistProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public List<Product> getAllProducts() {
        EntityManager em = emf.createEntityManager();
        List<Product> prods = (List<Product>) em.createNamedQuery("Product.searchAll").getResultList();
        em.close();
        return prods;
    }

    public Product getProductByID(int id) {
        EntityManager em = emf.createEntityManager();
        List<Product> prods = (List<Product>) em.createNamedQuery("Product.searchByID").setParameter("productid", id).getResultList();
        em.close();
        return prods.get(0);
    }

    public List<Product> getProductByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        List<Product> prods = (List<Product>) em.createNamedQuery("Product.searchByTitle").setParameter("title", title).getResultList();
        em.close();
        return prods;
    }

    public List<Product> getProductByManufacturer(String manufacturer) {
        EntityManager em = emf.createEntityManager();
        List<Product> prods = (List<Product>) em.createNamedQuery("Product.searchByManufacturer").setParameter("manufacturer", manufacturer).getResultList();
        em.close();
        return prods;
    }

    public List<Product> getProductByCategory(String category) {
        EntityManager em = emf.createEntityManager();
        List<Product> prods = (List<Product>) em.createNamedQuery("Product.searchByCategory").setParameter("category", category).getResultList();
        em.close();
        return prods;
    }

    public void mergeProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    public void removeProduct(Product product) {
        product.setActive(false);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    public void persistOrder(Orders order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    public void mergeOrder(Orders order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }

    public List<Orders> getAllOrders() {
        EntityManager em = emf.createEntityManager();
        List<Orders> orders = (List<Orders>) em.createNamedQuery("Order.searchAll").getResultList();
        em.close();
        return orders;
    }

    public List<Orders> getOrdersByCustomerID(String custID) {
        EntityManager em = emf.createEntityManager();
        List<Orders> orders = (List<Orders>) em.createNamedQuery("Order.searchByCustomer").setParameter("customerid", custID).getResultList();
        em.close();
        return orders;
    }

    public void removeOrder(Orders order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(order));
        em.getTransaction().commit();
        em.close();
    }







}
