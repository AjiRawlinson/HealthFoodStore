<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Health Food Shop | Login</title>
  </head>
  <body>
  <header>
    <div style="text-align: center"><img src="images/healthfoodstore.png"></div>
  </header>
  <hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">
    <h1>Customer Login</h1>
  <table>
    <form action="CreateCustomer" id="form1"><input type="hidden" name="purpose" value="custLogin" form="form1"></form>
      <form action="CreateCustomer.jsp" id="form2"></form>
      <tr>
        <td>Username:</td>
        <td><input type="text" name="custUsername" form="form1"></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="custPassword" form="form1"></td>
      </tr>
      <tr>
        <td><input type="submit" value="Log in" form="form1"></td>
        <td><input type="submit" value="Create Account" form="form2"></td>
      </tr>
  </table>
  <hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">

  <h1>AdminServlet Login</h1>
  <table>
    <form action="Admin"><input type="hidden" name="purpose" value="adminLogin">
      <tr>
        <td>Username:</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password"></td>
      </tr>
      <tr>
        <td><input type="submit" value="Log in"></td>
      </tr>
    </form>
  </table>
  <hr style="background: #D9CC40; color: darkgray; width:100%; left: 2%; height: 1%">

<%--  <div style="background: #D9CC40; width:96%; left: 2%; height: 2%"></div>--%>
  </body>
</html>
