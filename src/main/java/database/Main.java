/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dao.CountryDao;
import dao.SkillDao;
import dao.UserDao;
import daoImpl.CoutryDaoImpl;
import daoImpl.SkillDaoImpl;
import daoImpl.UserDaoImpl;
import modul.Country;
import modul.Skill;
import modul.User;

/**
 *
 * @author nur13
 */
public class Main {
    public static void main(String[] args) {

        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.getAll());
  

    
    }
}
