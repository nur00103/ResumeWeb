/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.AbstractDao;
import dao.SkillDao;
import modul.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nur13
 */
public class SkillDaoImpl extends AbstractDao implements SkillDao {

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> result =new ArrayList<>();
        try(Connection c=connect()){
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM SKILL");
            while(rs.next()){
                int id=rs.getInt("ID");
                String name=rs.getString("NAME");
                
                Skill skill=new Skill(id,name);
                result.add(skill);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Skill getById(int userId) {
        Skill usr = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM skill WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("ID");
                String name = rs.getString("NAME");

                usr = new Skill(id, name);

            }
        } catch (Exception ex) {

        }
        return usr;
    }

    @Override
    public boolean updateSkill(Skill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE SKILL SET NAME=? WHERE ID= ?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    public boolean insertSkill(Skill skl) {
        Connection conn;
        boolean b = true;
        try(Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("INSERT INTO SKILL (ID,NAME) VALUES (SKILL_S.NEXTVAL,?)"/*,Statement.RETURN_GENERATED_KEYS*/);
            stmt.setString(1, skl.getName());

          //  ResultSet generatedKeys=stmt.getGeneratedKeys();
          //      if (generatedKeys.next()){
          //          skl.setId(generatedKeys.getInt(1));
          //      }

            return stmt.execute();
        } catch (Exception ex) {
           ex.printStackTrace();
           return false;
        }

    }

    @Override
    public boolean removeSkill(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM SKILL WHERE ID=?");
            stmt.setInt(1, id);
            return stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public List<Skill> getByName(String sname) {
        List<Skill> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM SKILL WHERE NAME LIKE ?");
            stmt.setString(1, sname);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                list.add(new Skill(id, name));

            }
        } catch (Exception ex) {
            System.err.println("Heey, we have a problem");
        }
        return list;
    }


}
