package com.resumeweb.resumeweb;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import modul.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRequestUtil {

    public static void checkRequest(HttpServletRequest request, HttpServletResponse response){
            String userIdStr=request.getParameter("id");
            if (userIdStr==null || userIdStr.trim().isEmpty()){
               // response.sendRedirect("error.jsp");
                throw new IllegalArgumentException("id is not specified");
                //request.getRequestDispatcher("error.jsp").forward(request,response);
            }

    }
    public static User procesRequest(HttpServletRequest request,HttpServletResponse response) {
        User user=null;
        UserRequestUtil.checkRequest(request, response);
        Integer userId = Integer.parseInt(request.getParameter("id"));

        UserDao userDao = new UserDaoImpl();
        user = userDao.getById(userId);

        if (user == null) {
          throw new IllegalArgumentException("There is no user with this id");
        }
        return user;
    }
}
