/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import modul.Skill;

import java.util.List;

/**
 *
 * @author nur13
 */
public interface SkillDao {
    List<Skill> getAllSkill();

    public Skill getById(int id);

    boolean updateSkill(Skill u);

    boolean removeSkill(int id);

    public List<Skill> getByName(String name);

    public boolean insertSkill(Skill skl);
}