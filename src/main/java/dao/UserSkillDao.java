/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modul.UserSkill;

import java.util.List;

/**
 *
 * @author nur13
 */
public interface UserSkillDao {
    public List<UserSkill> getAllSkillByUserId(int id);

    public boolean insertUserSkill(UserSkill u);

    public boolean updateUserSkill(UserSkill u);

    public boolean removeUser(int id);

}
