import DAO.DataAccessObject;
import Entities.Customer;
import Entities.Orders;
import Entities.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/CreateCustomer")
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formPurpose = request.getParameter("purpose");

        switch(formPurpose) {
            case "searchProds":
                searchProducts(request, response);
                break;
            case "viewOrders":
                showOrders(request, response);
                break;
            case "buyBasket":
                buyBasket(request, response);
                break;
            default: //purpose is id number of product
                addToBasket(request, response, formPurpose);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Username");
        String password1 = request.getParameter("Password1");
        String password2 = request.getParameter("Password2");
        String address = request.getParameter("Address");
        String cardNo = request.getParameter("CardNo");
        String formPurpose = request.getParameter("purpose");

        switch (formPurpose) {
            case "custLogin":
                login(request, response);
                break;
            case "signOut":
                signOut(request, response);
                break;
            case "mergeCustomer":
                mergeCustomer(request, response, username, address, cardNo);
                break;
            case "removeCustomer":
                removeCustomer(request, response);
                break;
            case "newCust":
                newCustomer(request, response, username, password1, password2, address, cardNo);
        }

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("custUsername");
        String password = request.getParameter("custPassword");
        HttpSession sesh = request.getSession();

        DataAccessObject DAO = new DataAccessObject();
        Customer cust = DAO.getCustomerByUsername(username);

        if(cust.getUserName().equals(username) && cust.getPassword().equals(password)) {
            System.out.println("pasword works");
            sesh.setAttribute("CustID", "" + cust.getCustomerID());
            sesh.setAttribute("CustUsername", "" + cust.getUserName());
            ArrayList<Product> basket = new ArrayList<Product>();
            sesh.setAttribute("Basket", basket);
            response.sendRedirect("Customer.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username or Password is incorrect');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }


    }

    public void mergeCustomer(HttpServletRequest request, HttpServletResponse response, String username, String address, String cardNo) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        DataAccessObject DAO = new DataAccessObject();
        Customer cust = DAO.getCustomerByID(id);
        cust.setUserName(username);
        cust.setAddress(address);
        cust.setCardNumber(cardNo);
        DAO.mergeCustomer(cust);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Customer Updated');");
        out.println("location='Admin.jsp';");
        out.println("</script>");
    }

    public void removeCustomer(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));
        DataAccessObject DAO = new DataAccessObject();
        Customer cust = DAO.getCustomerByID(id);
        DAO.removeCustomer(cust);
    }

    public void newCustomer(HttpServletRequest request, HttpServletResponse response, String username, String password1, String password2, String address, String cardNo) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(validatePasswords(password1, password2)) {
            Customer cust = new Customer(username, password1, address, cardNo);
            DataAccessObject DAO = new DataAccessObject();
            DAO.persistCustomer(cust);
            response.sendRedirect("index.jsp");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Account Created');");
            out.println("location='index.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Passwords Dont Match');");
            out.println("location='CreateCustomer.jsp';");
            out.println("</script>");
        }
    }

    public void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchText");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("search: " + searchTerm);
        DataAccessObject DAO = new DataAccessObject();
        List<Product> productList;
        if(request.getParameter("searchTitle") != null) {
            productList = DAO.getProductByTitle(searchTerm);
        }
        else if(request.getParameter("searchManufacturer") != null) {
            productList = DAO.getProductByManufacturer(searchTerm);
        }
        else if(request.getParameter("searchCategory") != null) {
            productList = DAO.getProductByCategory(searchTerm);
        }
        else { //search All
            productList = DAO.getAllProducts();
        }

        if(productList.isEmpty()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No Products Found for that Search Query');");
            out.println("location='Customer.jsp';");
            out.println("</script>");
        } else {
            request.setAttribute("Products", productList);
            RequestDispatcher rd = request.getRequestDispatcher("CustomerSearch.jsp");
            rd.forward(request, response);
        }

    }

    public void addToBasket(HttpServletRequest request, HttpServletResponse response, String idString) throws ServletException, IOException {
        int productID = Integer.parseInt(idString.substring(1));
        HttpSession sesh = request.getSession();
        String custID = (String) sesh.getAttribute("CustID");
        ArrayList<Product> basket = (ArrayList<Product>) sesh.getAttribute("Basket");

        DataAccessObject DAO = new DataAccessObject();
        Product prod = DAO.getProductByID(productID);
        basket.add(prod);
        sesh.setAttribute("Basket", basket);

        for(Product p: basket) {
            System.out.println("Basket: " + p.getTitle());
        }

        request.setAttribute("Basket", basket);
        RequestDispatcher rd = request.getRequestDispatcher("/Customer.jsp");
        rd.forward(request, response);
    }

    public void buyBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesh = request.getSession();
        String custID = (String) sesh.getAttribute("CustID");
        ArrayList<Product> basket = (ArrayList<Product>) sesh.getAttribute("Basket");

        if(basket.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Basket is Empty');");
            out.println("location='Customer.jsp';");
            out.println("</script>");
        } else {
            DataAccessObject DAO = new DataAccessObject();
            Customer cust = DAO.getCustomerByID(Integer.parseInt(custID));
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            String today = date.format(new Date());

            Orders order = new Orders(today, cust, basket);
            DAO.persistOrder(order);

            basket.clear();
            sesh.setAttribute("Basket", basket);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Purchase Made');");
            out.println("location='Customer.jsp';");
            out.println("</script>");
        }
    }

    public void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesh = request.getSession();
        String custID = (String) sesh.getAttribute("CustID");
        DataAccessObject DAO = new DataAccessObject();
        Customer cust = DAO.getCustomerByID(Integer.parseInt(custID));
        List<Orders> orderList = (List<Orders>) DAO.getOrdersByCustomerID(custID);

        if(orderList.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Order List is Empty');");
            out.println("location='Customer.jsp';");
            out.println("</script>");
        } else {
            request.setAttribute("Orders", orderList);
            RequestDispatcher rd = request.getRequestDispatcher("/CustomerViewOrders.jsp");
            rd.forward(request, response);
        }
    }

    public void ErrorMessage(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert(\'" + msg + "\');");
        out.println("</script>");
    }

    public void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesh = request.getSession();
        sesh.invalidate();
        response.sendRedirect("index.jsp");
    }

    public boolean validatePasswords(String pass1, String pass2) {
        if(pass1.equals(pass2)) { return true; }
        return false;
    }
}
