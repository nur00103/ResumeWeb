package com.resumeweb.resumeweb.controller;

import com.resumeweb.resumeweb.util.ControllerUtil;
import dao.UserDao;
import daoImpl.UserDaoImpl;
import modul.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private UserDao userDao=new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           String email=request.getParameter("email");
           String password=request.getParameter("password");
           User user=userDao.findByEmailAndPassword(email,password);
           if (user==null){
               throw new IllegalArgumentException("Email or password is incorrect!");
           }
           request.getSession().setAttribute("loggedInUser",user);
           response.sendRedirect("users");
       }catch (Exception e){
           ControllerUtil.errorPage(response,e);

       }
    }
}
