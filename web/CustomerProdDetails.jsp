<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 09/05/2020
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>
<div style="width: 30%; position: absolute;	top:40%; left: 35%; padding: 15px; text-align: center; border: 2px solid black">
    <h2>Register Your Details</h2>
    <table style="margin: auto">
        <form action="CreateCustomer" method="post" ><input type="hidden" value="addProd" name="purpose">
            <tr>
                <td>ID: </td>
                <td>${product.productid}</td>
            </tr>
            <tr>
                <td>Title:</td>
                <td>${product.title} </td>
            </tr>
            <tr>
                <td>Manufacturer: </td>
                <td>${product.manufacturer}</td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>${product.category}</td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>€${product.price}</td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Buy"></td>
            </tr>
        </form>
    </table>
</div>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<form action="CreateCustomer" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
