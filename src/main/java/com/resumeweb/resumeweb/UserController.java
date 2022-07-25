package com.resumeweb.resumeweb;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import modul.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/UserController")
public class UserController extends HttpServlet {

    UserDao userDao=new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.valueOf(request.getParameter("id"));
        String name=request.getParameter("name");
        String surname=request.getParameter("surname");

        User user=userDao.getById(id);
        user.setName(name);
        user.setSurname(surname);
        userDao.updateUser(user);


        response.sendRedirect("user.jsp");

    }
}
