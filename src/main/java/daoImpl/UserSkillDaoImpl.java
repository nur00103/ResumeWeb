/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.AbstractDao;
import dao.UserSkillDao;
import modul.Skill;
import modul.User;
import modul.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nur13
 */
public class UserSkillDaoImpl extends AbstractDao implements UserSkillDao {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("ID");
        int skillId = rs.getInt("SKILL_ID");
        int userId = rs.getInt("USER_ID");
        int power = rs.getInt("POWER_SKILL");
        String skillName = rs.getString("SKILL_NAME");

        User u1=new User(userId);
        Skill s1=new Skill(skillId,skillName);

        return new UserSkill(id,u1,s1,power);
    }

    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement p = c.prepareStatement("SELECT U.*,US.USER_ID,US.SKILL_ID,S.NAME SKILL_NAME,US.POWER POWER_SKILL FROM USER_SKILL US "
                    + "LEFT JOIN USERR U ON U.ID=US.USER_ID "
                    + "LEFT JOIN SKILL S ON S.ID=US.SKILL_ID "
                    + "WHERE US.USER_ID=? ");
            p.setInt(1, userId);
            p.execute();
            ResultSet rs = p.getResultSet();
            while (rs.next()) {
                UserSkill us = getUserSkill(rs);
                result.add(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean insertUserSkill(UserSkill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO USER_SKILL (ID,SKILL_ID , USER_ID ,POWER) VALUES (USER_SKILL_S.NEXTVAL,? , ? ,  ? ) ");

            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    public boolean updateUserSkill(UserSkill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE USER_SKILL SET SKILL_ID = ? , USER_ID =? ,POWER =?  WHERE ID = ? ");

            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeUser(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM USER_SKILL WHERE ID=?");
            stmt.setInt(1, id);
            System.out.println("id :" + String.valueOf(id));
            return stmt.execute();

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }



}