/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import modul.User;

import java.util.List;

/**
 *
 * @author nur13
 */
public interface UserDao {
    public List<User> getAll(String name,String surname,Integer nationalityId);

    public List<User> getAll();

    public boolean updateUser(User u);

    public boolean addUser(User u);

    public boolean removeUser(int id);

    public User getById(int id);



}
