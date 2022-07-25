/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dao.*;
import daoImpl.*;


/**
 *
 * @author nur13
 */
public class Context {
    
    public static UserDao instanceUserDao(){
        return new UserDaoImpl();
    }
    public static UserSkillDao instanceUserSkillDao(){
        return new UserSkillDaoImpl();
    }
    public static EmploymentHistoryDao instanceEmploymentHistoryDao(){
        return new EmploymentHistoryDaoImpl();
    }
    
    public static CountryDao instanceCountryDao(){
        return new CoutryDaoImpl();
    }
    
    public static SkillDao instanceSkillDao(){
        return new SkillDaoImpl();
    }
}
