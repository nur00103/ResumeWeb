<%@ page import="dao.UserDao" %>
<%@ page import="daoImpl.UserDaoImpl" %>
<%@ page import="modul.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nur13
  Date: 22.07.2022
  Time: 00:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8">
    <title>JSP Page</title>
    <style><%@include file="/WEB-INF/css/users.css"%></style>
    <link rel="stylesheet" type="text/css" href="WEB-INF/css/users.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
  <%
    UserDao userDao=new UserDaoImpl();
    String name=request.getParameter("name");
    String surname=request.getParameter("surname");
    String nationalityIdStr=request.getParameter("nid");
    Integer nationalityId=null;
    if (nationalityIdStr!=null && !nationalityIdStr.trim().isEmpty()){
        nationalityId=Integer.parseInt(nationalityIdStr);
    }
    List<User> list=userDao.getAll(name,surname,nationalityId);


  %>
<div class="container">
 <div class="row">
   <div class="col-4" >
  <form action="users.jsp" method="GET">
      <div class="form-group">
         <label>Name:</label>
         <input placeholder="Enter name" class="form-control" type="text" name="name" value=""/>
      </div>

      <div class="form-group">
         <label>Surname:</label>
         <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
      </div>
<br>
      <button type="submit" class="btn btn-primary">Search</button>


  </form>

</div>
 </div>
    <hr/>

 <div >
    <table class="table">
        <thead>
          <tr>
              <th>Name</th>
              <th>Surname</th>
              <th>Nationality</th>
              <th>Operations</th>

          </tr>
        </thead>

        <tbody>
        <%for(User user : list){%>
          <tr>
              <td><%=user.getName()%></td>
              <td><%=user.getSurname()%></td>
              <td><%=user.getNationality().getName()==null?"":user.getNationality().getName()%></td>
              <td>

                  <button class="btn btn-danger" type="submit" name="action" value="delete">
                      <i class="fas fa-trash-alt"></i>
                  </button>
                  <button class="btn btn-secondary" type="submit" name="action" value="update">
                      <i class="fas fa-pen-square"></i>
                  </button >


              </td>
          </tr>
        <%}%>
        </tbody>
    </table>

</div>

</div>
</body>
</html>
