<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 04/05/2020
  Time: 14:05
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
    <h2>Enter Product Details</h2>
    <table style="margin: auto">
        <form action="Admin" method="get" ><input type="hidden" value="newProd" name="purpose">
        <tr>
            <td>Title: </td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>Manufacturer: </td>
            <td><input type="text"  name="manufacturer"></td>
        </tr>
        <tr>
            <td>Category: </td>
            <td><input type="text" name="category"></td>
        </tr>
        <tr>
            <td>Price: </td>
            <td>€<input type="number"  name="price"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
        </tr>
        </form>
    </table>
</div>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<form action="Admin" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
