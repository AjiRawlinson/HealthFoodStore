import DAO.DataAccessObject;
import Entities.Administrator;
import Entities.Customer;
import Entities.Orders;
import Entities.Product;
import IteratorPattern.CustomerRepository;
import IteratorPattern.Iterator;
import IteratorPattern.OrderRepository;
import IteratorPattern.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formPurpose = request.getParameter("purpose");
        switch (formPurpose) {
            case "allCusts":
                searchCusts(request, response);
                break;
            case "allProds": //No more Catholics
                searchProds(request, response);
                break;
            case "allOrders":
                viewOrders(request, response);
                break;
            default:// customer or product id?
                editData(request, response, formPurpose);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        String formPurpose = request.getParameter("purpose");
        switch (formPurpose) {
            case "adminLogin":
                login(request, response);
                break;
            case "signOut":
                signOut(request, response);
                break;
            case "newProd":
                createProduct(request, response);
                break;
            case "mergeProduct":
                mergeProduct(request, response, Integer.parseInt(id));
                break;
            case "removeProduct": //No more Catholics
                removeProduct(request, response, Integer.parseInt(id));
                break;
            default:// customer or product id?
                editData(request, response, formPurpose);
        }


    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Administrator admin = new Administrator();
        DataAccessObject DAO = new DataAccessObject();
        admin = DAO.getAdminByUsername(username);

        if(admin.getUserName().equals(username) && admin.getPassword().equals(password)) {
            response.sendRedirect("Admin.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username or Password is incorrect');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }

    }

    public void editData(HttpServletRequest request, HttpServletResponse response, String idString) throws ServletException, IOException {
        String table = "" + idString.charAt(0);
        String id = idString.substring(1);
        switch (table) {
            case "c":
                if(isCustomerID(id)){
                    editCust(request, response, Integer.parseInt(id));
                }
                break;
            case "p":
                if(isProductID(id)){
                    editProduct(request, response, Integer.parseInt(id));
                }
                break;
        }
    }

    public void searchCusts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DataAccessObject DAO = new DataAccessObject();
        List<Customer> custs = new ArrayList<Customer>();
        CustomerRepository custRepository = new CustomerRepository();

        for(Iterator iter = custRepository.getIterator(); iter.hasNext();){
            Customer cust = (Customer) iter.next();
            if(cust != null) { custs.add(cust); }
        }
        request.setAttribute("Customers", custs);
        RequestDispatcher rd = request.getRequestDispatcher("/AdminSearch.jsp");
        rd.forward(request, response);
    }

    public void searchProds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DataAccessObject DAO = new DataAccessObject();
        List<Product> prods =  new ArrayList<Product>();
        ProductRepository prodRepository = new ProductRepository();

        for(Iterator iter = prodRepository.getIterator(); iter.hasNext();){
            Product prod = (Product) iter.next();
            if(prod != null) { prods.add(prod); }
        }
        request.setAttribute("Products", prods);
        RequestDispatcher rd = request.getRequestDispatcher("/AdminSearch.jsp");
        rd.forward(request, response);
    }


    public void editCust(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        DataAccessObject DAO = new DataAccessObject();
        Customer c =  DAO.getCustomerByID(id);
        request.setAttribute("id", c.getCustomerID());
        request.setAttribute("username", c.getUserName());
        request.setAttribute("address", c.getAddress());
        request.setAttribute("cardNo", c.getCardNumber());

        RequestDispatcher rd = request.getRequestDispatcher("/CustomerEdit.jsp");
        rd.forward(request, response);
    }

    public void editProduct(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
        DataAccessObject DAO = new DataAccessObject();
        Product p =  DAO.getProductByID(id);
        request.setAttribute("id", p.getProductID());
        request.setAttribute("title", p.getTitle());
        request.setAttribute("manufacturer", p.getManufacturer());
        request.setAttribute("category", p.getCategory());
        request.setAttribute("price", p.getPrice());

        RequestDispatcher rd = request.getRequestDispatcher("/ProductEdit.jsp");
        rd.forward(request, response);
    }

    public boolean isCustomerID(String check) {
        try {
            int numCheck = Integer.parseInt(check);
            DataAccessObject DAO = new DataAccessObject();
            List<Customer> custs =  (List<Customer>) DAO.getAllCustomer();
            for(Customer c: custs) {
                if(c.getCustomerID() == numCheck) { return true; }
            }
        } catch (NumberFormatException e) { }
        return false;
    }

    public boolean isProductID(String check) {
        try {
            int numCheck = Integer.parseInt(check);
            DataAccessObject DAO = new DataAccessObject();
            List<Product> prods =  (List<Product>) DAO.getAllProducts();
            for(Product p: prods) {
                if(p.getProductID() == numCheck) { return true; }
            }
        } catch (NumberFormatException e) { }
        return false;
    }

    public void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String manufacturer = request.getParameter("manufacturer");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));

        DataAccessObject DAO = new DataAccessObject();
        Product prod = new Product(title, manufacturer, category, price);
        DAO.persistProduct(prod);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Product Created');");
        out.println("location='Admin.jsp';");
        out.println("</script>");
    }

    public void mergeProduct(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        String title = request.getParameter("title");
        String manufacturer = request.getParameter("manufacturer");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));

        DataAccessObject DAO = new DataAccessObject();
        Product prod = DAO.getProductByID(id);
        prod.setTitle(title);
        prod.setManufacturer(manufacturer);
        prod.setCategory(category);
        prod.setPrice(price);

        DAO.mergeProduct(prod);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Product Updated');");
        out.println("location='Admin.jsp';");
        out.println("</script>");
    }

    public void removeProduct(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        DataAccessObject DAO = new DataAccessObject();
        Product prod = DAO.getProductByID(id);
        DAO.removeProduct(prod);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Product Deleted');");
        out.println("location='Admin.jsp';");
        out.println("</script>");
    }

    public void viewOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataAccessObject DAO = new DataAccessObject();
        List<Orders> orderList = new ArrayList<>();
        OrderRepository orderRepository = new OrderRepository();

        for(Iterator iter = orderRepository.getIterator(); iter.hasNext();){
            Orders order = (Orders) iter.next();
            if(order != null) { orderList.add(order); }
        }
        request.setAttribute("Orders", orderList);
        RequestDispatcher rd = request.getRequestDispatcher("/AdminViewOrders.jsp");
        rd.forward(request, response);
    }

    public void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
}