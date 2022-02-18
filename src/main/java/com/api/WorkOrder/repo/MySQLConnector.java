package com.api.WorkOrder.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MySQLConnector  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Connection getConnection() {
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/POOJA_CLIENT?"
                    + "user=root&password=Sk4173174@");
            System.out.println("Connection is made.");
            return conn;
        } catch (SQLException ex) {
            // handle any errors
        }
        return null;
    }


    public int getStatus() throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT DEPTNO FROM emp WHERE DEPTNO=1";
        int deptno=0;

        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                deptno = rs.getInt("deptno");
                System.out.println("deptno: "+deptno);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return deptno;
    }

}
