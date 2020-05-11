<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 27/04/2020
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Health Food Store | Create Customer</title>
</head>
<body>
<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>
<div style="width: 30%; position: absolute;	top:40%; left: 35%; padding: 15px; text-align: center; border: 2px solid black">
    <h2>Register Your Details</h2>
<table style="margin: auto">
<form action="CreateCustomer"><input type="hidden" name="purpose" value="newCust">
    <tr>
        <td>Username: </td>
        <td><input type="text" name="Username"></td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><input type="text" name="Password1"></td>
    </tr>
    <tr>
        <td>Confirm Password:</td>
        <td><input type="text" name="Password2"></td>
    </tr>
    <tr>
        <td>Address:</td>
        <td><input type="text" name="Address"></td>
    </tr>
    <tr>
        <td>Card no.: </td>
        <td><input type="text" name="CardNo"></td>
    </tr>
    <tr>
        <td><input type="submit" value="Register"></td>
    </tr>
</form>
</table>
</div>

<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
</body>
</html>
