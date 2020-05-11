<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 10/05/2020
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>

<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<c:forEach items="${Orders}" var="order">
    <table>
        <tr>
            <th>Customer ID: ${order.customerID}</th>
            <th>Order Number: ${order.orderID}</th>
            <th>${order.date}</th>
        </tr>
        <c:forEach items="${order.products}" var="product" >
            <tr>
                <td>${product.id}</td>
                <td>${product.title}</td>
                <td>€${product.price}</td>
            </tr>
        </c:forEach>
        <th colspan="2">Total Price </th>
        <th>€${order.totalPrice}</th>
    </table>
    <hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%"></c:forEach>
<form action="Admin" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
