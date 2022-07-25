/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.AbstractDao;
import dao.CountryDao;
import modul.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nur13
 */
public class CoutryDaoImpl extends AbstractDao implements CountryDao {

    public Country getCountry(ResultSet rs) throws SQLException {

        int id = rs.getInt("ID");
        String name = rs.getString("COUNTRY");
        String nationality = rs.getString("NATIONALITY");

        Country contry = new Country(id, name, nationality);
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM COUNTRY");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Country contry = getCountry(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Country getById(int userId) {
        Country el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM COUNTRY WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getCountry(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateCountry(Country u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE COUNTRY SET COUNTRY=?,NATIONALITY=? WHERE ID= ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean insertCountry(Country u) {
        Connection conn;
        boolean b = true;
        try(Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("INSERT INTO COUNTRY (ID,COUNTRY ,NATIONALITY) VALUES (COUNTRY_S.NEXTVAL,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean removeCountry(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM COUNTRY WHERE ID=?");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

}