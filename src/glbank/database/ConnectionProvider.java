/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glbank.database;

import glbank.Client;
import glbank.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, newPassword);
                ps.setInt(2, idemp);
                ps.execute();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    }

    public List<Client> getAllClients() {
        String query = "SELECT * FROM Clients "
                + "INNER JOIN ClientDetails ON Clients.idc = ClientDetails.idc "
                + "WHERE disable = 'F' "
                + "ORDER BY lastname, firstname";
        Connection conn = getConnection();
        List<Client> list = new ArrayList<>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    int idc = rs.getInt("Clients.idc");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    Date dob = rs.getDate("dob");
                    Client client = new Client(idc, lastName, firstName, dob);
                    list.add(client);
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return list;
    }

    public List<String> getClientLogins() {
        String query = "SELECT login FROM LoginClient";
        Connection conn = getConnection();
        List<String> list = new ArrayList<>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String login = rs.getString("login");
                    list.add(login);
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
        return list;
    }

    public boolean addClientIntoDatabase(Client client, String password) {
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        Date dob = client.getDob();
        String email = client.getEmail();
        String street = client.getStreet();
        int houseNumber = client.getHouseNumber();
        String postCode = client.getPostCode();
        String userName = client.getUserName();
        String city = client.getCity();
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            if (conn != null) {
                if (insertIntoClients(firstName, lastName, conn)) {
                    // if inserting into clients table was successful then do next inserts
                    if (insertIntoClientDetails(getClientIdc(firstName, lastName, conn),
                            street, houseNumber, postCode, dob, email, city, conn)) {
                        // after these two insert do last insert
                        if (insertIntoLoginClient(getClientIdc(firstName, lastName, conn),
                                userName, password, conn)) {
                            conn.commit();
                            conn.close();
                            return true;
                        }
                    }
                }
            }
            conn.rollback();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("addClientIntoDatabase Error: " + ex.toString());
        }
        return false;

    }

    private boolean insertIntoClients(String firstName, String lastName, Connection conn) {
        String query = "INSERT INTO Clients (firstname, lastname) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println("insertIntoClients Error: " + ex.toString());
        }
        return false;

    }

    private int getClientIdc(String firstName, String lastName, Connection conn) {
        int idc = -1;
        String querySelect = "SELECT max(idc) FROM Clients WHERE "
                + "firstname LIKE ? AND lastname LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(querySelect)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idc = rs.getInt("max(idc)");
            }
        } catch (SQLException ex) {
            System.out.println("getClientIdc Error: " + ex.toString());
        }
        return idc;
    }

    private boolean insertIntoClientDetails(int idc, String street, int houseNumber,
            String postCode, Date dob, String email, String city, Connection conn) {

        String query = "INSERT INTO ClientDetails (idc, street, housenumber, "
                + "postcode, dob, email, city) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idc);
            ps.setString(2, street);
            ps.setInt(3, houseNumber);
            ps.setString(4, postCode);
            ps.setDate(5, (java.sql.Date) dob);
            ps.setString(6, email);
            ps.setString(7, city);

            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("insertIntoClientDetails Error: " + ex.toString());
        }
        return false;
    }

    private boolean insertIntoLoginClient(int idc, String login, String password, Connection conn) {
        String query = "INSERT INTO LoginClient (idc, login, password) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idc);
            ps.setString(2, login);
            ps.setString(3, password);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("insertIntoLoginClient Error: " + ex.toString());
        }
        return false;
    }

}
