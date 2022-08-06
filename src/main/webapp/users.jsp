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

    <script><%@include file="WEB-INF/js/users.js"%></script>
    <script type="text/javascript" src="/WEB-INF/js/users.js"></script>
    <!--Bootstrap import -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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
         <input onkeyup="whatIamTyping()" placeholder="Enter name" class="form-control" type="text" name="name" value=""/>

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
              <th></th>
              <th></th>

          </tr>
        </thead>

        <tbody>
        <%for(User user : list){%>
          <tr>
              <td><%=user.getName()%></td>
              <td><%=user.getSurname()%></td>
              <td><%=user.getNationality().getName()==null?"":user.getNationality().getName()%></td>
              <td style="width: 5px">

                      <input type="hidden" name="id" value="<%=user.getId()%>"/>
                      <input type="hidden" name="action" value="delete"/>
                  <button onclick="setIdForDelete('<%=user.getId()%>')" class="btn btn-danger" type="submit" value="delete" data-toggle="modal" data-target="#exampleModal">
                      <i class="fas fa-trash-alt"></i>
                  </button>

              </td>
              <td style="width: 5px">
                  <form action="userdetail" method="GET">
                      <input type="hidden" name="id" value="<%=user.getId()%>"/>
                      <input type="hidden" name="action" value="update"/>
                      <button class="btn btn-secondary" type="submit" value="update">
                          <i class="fas fa-pen-square"></i>
                      </button >
                  </form>
              </td>
          </tr>
        <%}%>
        </tbody>
    </table>

</div>

</div>


  <!-- Modal for delete button -->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                  </button>
              </div>
              <div class="modal-body">
                  Are you sure?
              </div>
              <div class="modal-footer">
                  <form action="userdetail" method="POST">
                      <input type="hidden" name="id" value="" id="idForDelete"/>
                      <input type="hidden" name="action" value="delete"/>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <input type="submit" class="btn btn-danger" value="Delete"/>
                  </form>
              </div>
          </div>
      </div>
  </div>


</body>
</html>
