<%--
  Created by IntelliJ IDEA.
  User: aaaji
  Date: 03/05/2020
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Edit</title>
</head>
<body>
<header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
</header>
<div style="width: 30%; position: absolute;	top:40%; left: 35%; padding: 15px; text-align: center; border: 2px solid black">
    <h2>Register Your Details</h2>
    <table style="margin: auto">
        <form action="CreateCustomer" id="form1"></form><input type="hidden" value="mergeCustomer" name="purpose" >
        <tr>
            <td>ID:</td>
            <td><input type="text" name="id" value="${id}" form="form1" readonly></td>
        </tr>
            <tr>
                <td>Username: </td>
                <td><input type="text" value="${username}" name="Username" form="form1"></td>
            </tr>

            <tr>
                <td>Address:</td>
                <td><input type="text"  value="${address}"name="Address" form="form1"></td>
            </tr>
            <tr>
                <td>Card no.: </td>
                <td><input type="text" value="${cardNo}" name="CardNo" form="form1"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save" form="form1"></td>
                <td><form action="CreateCustomer"><input type="hidden" value="removeCustomer" name="purpose"><input type="hidden" value="${id}" name="id">
                    <input type="submit" value="Delete"></form></td>
            </tr>
    </table>
</div>
<hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
<form action="Admin" method="get">
    <input type="hidden" value="signOut" name="purpose">
    <input type="submit" name="signOut" value="Sign Out">
</form>
</body>
</html>
