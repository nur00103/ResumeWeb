/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImpl;

import dao.AbstractDao;
import dao.EmploymentHistoryDao;
import modul.EmploymentHistory;
import modul.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nur13
 */
public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDao {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
       String header=rs.getString("HEADER");
       String jobDescription=rs.getString("JOB_DESCRIPTION");
       Date beginDate=rs.getDate("BEGIN_DATE");
       Date endDate=rs.getDate("END_DATE");
       int userId=rs.getInt("USER_ID");
       
       EmploymentHistory eh=new EmploymentHistory(null,header,beginDate,endDate,jobDescription,new User(userId));
       return eh;
    }

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {

            PreparedStatement p = c.prepareStatement("SELECT * FROM EMPLOYMENT_HISTORY WHERE USER_ID=?");
            p.setInt(1, userId);
            p.execute();
            ResultSet rs = p.getResultSet();
            while (rs.next()) {
                EmploymentHistory eh = getEmploymentHistory(rs);
                result.add(eh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    

   

}