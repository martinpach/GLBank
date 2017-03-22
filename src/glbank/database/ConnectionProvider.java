/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glbank.database;

import glbank.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Martin
 */
public class ConnectionProvider {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DBNAME = "GLBank";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL + DBNAME, USERNAME, PASSWORD);
            Class.forName(DRIVER).newInstance();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        return conn;
    }

    public boolean isEmployeePasswordValid(String username, String password) {
        String query = "SELECT idemp FROM LoginEmployee WHERE login LIKE BINARY ? "
                + "AND password LIKE BINARY ?";
        Connection conn = getConnection();
        boolean result = false;
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                result = rs.next();
                conn.close();
                return result;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return false;
    }

    public boolean isEmployeePasswordValid(int idemp, String password) {
        String query = "SELECT idemp FROM LoginEmployee WHERE idemp LIKE "
                + "BINARY ? "
                + "AND password LIKE BINARY ?";
        Connection conn = getConnection();
        boolean result;
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, idemp);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                result = rs.next();
                conn.close();
                return result;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return false;
    }

    public int getEmployeeId(String username) {
        String query = "SELECT idemp FROM LoginEmployee WHERE login LIKE BINARY ?";
        Connection conn = getConnection();
        int id = -1;

        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    id = rs.getInt("idemp");
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return id;
    }

    public void logEmployeeAccess(int id) {
        String query = "INSERT INTO historyloginemployee(idemp, logindate) "
                + "VALUES(?, ?)";
        String date = getDateTime();
        Connection conn = getConnection();
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, date);
                ps.execute();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    }

    public String getDateTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public Employee getEmployee(int id) {
        String query = "SELECT * FROM Employees WHERE idemp LIKE ?";
        Connection conn = getConnection();
        Employee employee = null;
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    employee = new Employee(rs.getInt("idemp"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("email"),
                            rs.getString("position").charAt(0));
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return employee;
    }

    public void changePassword(int idemp, String newPassword) {
        String query = "UPDATE LoginEmployee SET password = ? WHERE idemp LIKE ?";
        Connection conn = getConnection();
        if (conn != null) {
            try(PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, newPassword);
                ps.setInt(2, idemp);
                ps.execute();
                conn.close();
            }
            catch(SQLException ex){
                System.out.println("Error: " + ex.toString());
            }
        }
    }

}
