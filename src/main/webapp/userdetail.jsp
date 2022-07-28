<%@ page import="dao.UserDao" %>
<%@ page import="daoImpl.UserDaoImpl" %>
<%@ page import="modul.User" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html ; charset-UTF-8">
    <title>JSPs</title>
    <style><%@include file="/WEB-INF/css/users.css"%></style>
    <link href="/WEB-INF/css/users.css" rel="stylesheet" type="text/css">
</head>
<body>
  <%
      User user=(User) request.getAttribute("user");
  %>
<div class="context-menu">
  <form action="UserController" method="POST">
      <input type="hidden" name="id" value="<%=user.getId()%>"/>

      <label>Name:</label>
      <input type="text" name="name" value="<%=user.getName()%>"/>
      <br/>

      <label>Surname:</label>
      <input type="text" name="surname" value="<%=user.getSurname()%>"/>
      <br/>

      <input type="submit" name="save" value="Save">

  </form>

</div>

</body>
</html>
