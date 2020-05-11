<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 04/05/2020
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>

<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<table style="border: 2px solid black;">
    <tr><th style="border: 1px solid black; width: 150px;">ID</th><th style="border: 1px solid black; width: 150px;">Name</th><th style=" width: 150px;"></th></tr>
    <c:forEach items="${Products}" var="product">
        <tr >
            <td style="border: 1px solid black;">p${product.productID}</td>
            <td style="border: 1px solid black;">${product.title}</td>
            <td style="border: 1px solid black;"><form action="CreateCustomer" method="Post" > <input type="submit" value="Add to Basket"> <input type="hidden" name="purpose" value="p${product.productID}"></form></td>
        </tr>
    </c:forEach>
</table>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<form action="CreateCustomer" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
