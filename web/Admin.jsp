<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 27/04/2020
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="fnt" uri="" %>--%>
<html>
<head style="margin: auto">
    <title>Health Food Shop | AdminServlet</title>
</head>
<body>
<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>

<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
    <h1>Welcome AdminServlet</h1>
<table>
    <tr><td>
    <form action="Admin" method="post">
        <input type="hidden" value="allCusts" name="purpose">
        <input type="submit" name="searchAll" value="Search All Customers">
    </form>
    </td>

    <td>
    <form action="Admin" method="post">
        <input type="hidden" value="allProds" name="purpose">
        <input type="submit" name="searchProds" value="Search All Products">
    </form>
    </td>

    <td>
    <form action="Admin" method="post">
        <input type="hidden" value="allOrders" name="purpose">
        <input type="submit" name="viewOrders" value="View All Orders">
    </form>
    </td>

    <td>
    <form action="CreateProduct.jsp">
        <input type="submit" name="createProd" value="Create New Product">
    </form>
    </td></tr>
</table>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<form action="Admin" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
