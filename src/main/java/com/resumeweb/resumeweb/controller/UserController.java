package com.resumeweb.resumeweb.controller;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import modul.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/userdetail")
public class UserController extends HttpServlet {

    UserDao userDao=new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }
            Integer userId = Integer.parseInt(request.getParameter("id"));

            UserDao userDao = new UserDaoImpl();
            User user = userDao.getById(userId);

            if (user == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("owner", true);
            request.setAttribute("user",user);
            request.getRequestDispatcher("userdetail.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("error?msg="+e.getMessage());
        }
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


        response.sendRedirect("userdetail.jsp");

    }
}
