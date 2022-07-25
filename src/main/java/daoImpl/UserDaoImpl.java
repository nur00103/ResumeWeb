/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.AbstractDao;
import dao.UserDao;
import modul.Country;
import modul.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nur13
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("ID");
        String name = rs.getString("NAME");
        String surname = rs.getString("SURNAME");
        String email = rs.getString("EMAIL");
        String phone=rs.getString("PHONE");
        String address=rs.getString("ADDRESS");
        String profileDesc=rs.getString("PROFILE_DESCRIPTION");
        int nationalityId = rs.getInt("NATIONALITY_ID");
        int birthPlaceId = rs.getInt("BIRTHPLACE_ID");
        String nationalityStr = rs.getString("NATIONALITY");
        String birthPlaceStr = rs.getString("COUNTRY");
        Date birthDate = rs.getDate("BIRTH_DATE");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPlaceStr, null);

        return(new User(id, name, surname, email, phone, address, profileDesc, birthDate, nationality, birthPlace));
    }

    @Override
    public List<User> getAll(String name,String surname,Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection connection = connect()) {


            String sql="SELECT "
                    + "U.*, "
                    + "N.NATIONALITY, "
                    + "C.COUNTRY "
                    + "FROM USERR U "
                    + "LEFT JOIN COUNTRY N ON U.NATIONALITY_ID=N.ID "
                    + "LEFT JOIN COUNTRY C ON U.BIRTHPLACE_ID=C.ID WHERE 1=1";
            if (name!=null && !name.trim().isEmpty()){
                sql+="AND U.NAME=? ";
            }
            if(surname!=null && !surname.trim().isEmpty()){
                sql+="AND U.SURNAME=? ";
            }
            if (nationalityId!=null ){
                sql+="AND U.NATIONALITY_ID=? ";
            }
            PreparedStatement stmt=connection.prepareStatement(sql);

            int i=1;
            if (name!=null && !name.trim().isEmpty()){
                stmt.setString(i,name);
                i++;
            }
            if (surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i,surname);
                i++;
            }
            if (nationalityId!=null){
                stmt.setString(i,nationalityId.toString());
            }


            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connect()) {


            String sql="SELECT "
                    + "U.*, "
                    + "N.NATIONALITY, "
                    + "C.COUNTRY "
                    + "FROM USERR U "
                    + "LEFT JOIN COUNTRY N ON U.NATIONALITY_ID=N.ID "
                    + "LEFT JOIN COUNTRY C ON U.BIRTHPLACE_ID=C.ID WHERE 1=1";

            PreparedStatement stmt=connection.prepareStatement(sql);


            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement p = c.prepareStatement("UPDATE USERR SET NAME=?,SURNAME=?,EMAIL=?,PHONE=?,ADDRESS=?,PROFILE_DESCRIPTION=? WHERE ID=?");
            p.setString(1, u.getName());
            p.setString(2, u.getSurname());
            p.setString(3, u.getEmail());
            p.setString(4, u.getPhone());
            p.setString(5, u.getAddress());
            p.setString(6, u.getProfileDesc());
            p.setInt(7, u.getId());
            return p.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("DELETE FROM USERR WHERE ID=1");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection connection = connect()) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT "
                    + "U.*, "
                    + "N.NATIONALITY, "
                    + "C.COUNTRY "
                    + "FROM USERR U "
                    + "LEFT JOIN COUNTRY N ON U.NATIONALITY_ID=N.ID "
                    + "LEFT JOIN COUNTRY C ON U.BIRTHPLACE_ID=C.ID WHERE U.ID=" + userId);
            while (rs.next()) {

                result = getUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement p = c.prepareStatement("INSERT INTO USERR(ID,NAME,SURNAME,EMAIL,PHONE,ADDRESS,PROFILE_DESCRIPTION) VALUES(?,?,?,?,?,?,?)");
            p.setInt(1, u.getId());
            p.setString(2, u.getName());
            p.setString(3, u.getSurname());
            p.setString(4, u.getEmail());
            p.setString(5, u.getPhone());
            p.setString(6, u.getAddress());
            p.setString(7, u.getProfileDesc());
            return p.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
