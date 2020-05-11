<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 04/05/2020
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
String uname=(String)session.getAttribute("CustUsername");
%>
<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>

<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
    <h1>Welcome <%=uname %> </h1>
<table>
    <form action="CreateCustomer" method="post"><input type="hidden" value="searchProds" name="purpose">
        <tr><td colspan="4"><input type="text" name="searchText"  placeholder="search here"/></td></tr>
        <tr>
            <td>
                <input type="submit" name="searchTitle" value="Search Title">
            </td>
            <td>
                <input type="submit" name="searchManufacturer" value="Search Manufacturer">
            </td>
            <td>
                <input type="submit" name="searchCategory" value="Search Category">
            </td>
            <td>
                <input type="submit" name="searchAll" value="Search All Products">
            </td>
        </tr>
    </form>
</table>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
    <form action="CreateCustomer" method="post">
        <input type="hidden" value="viewOrders" name="purpose">
        <input type="submit" name="searchProds" value="View Orders">
    </form>

    <table style="border: 1px solid black; width: 140px;">
        <tr>
            <th style="border: 1px solid black;">Basket</th><th  style="border: 1px solid black;">items: (${Basket.size()})</th>
        </tr>
        <c:forEach items="${Basket}" var="product">
            <tr>
                <td  style="border: 1px solid black;">${product.title}</td>
                <td  style="border: 1px solid black;">${product.price}</td>
            </tr>
        </c:forEach>
        <tr>
            <td  colspan="2" style="border: 1px solid black;"><form action="CreateCustomer" method="Post" > <input type="submit" value="Buy"> <input type="hidden" name="purpose" value="buyBasket"></form></td>
        </tr>
    </table>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<form action="CreateCustomer" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
