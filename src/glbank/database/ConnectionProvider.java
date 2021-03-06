/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glbank.database;

import glbank.Account;
import glbank.BankTransaction;
import glbank.Card;
import glbank.CashTransaction;
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
            System.out.println("getConnection Error: " + ex.toString());
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
                return result;
            } catch (SQLException ex) {
                System.out.println("isEmployeePasswordValid Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                return result;
            } catch (SQLException ex) {
                System.out.println("isEmployeePasswordValid Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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

            } catch (SQLException ex) {
                System.out.println("getEmployeeId Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            } catch (SQLException ex) {
                System.out.println("logEmployeeAccess Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            } catch (SQLException ex) {
                System.out.println("getEmployee Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            } catch (SQLException ex) {
                System.out.println("changePassword Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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

            } catch (SQLException ex) {
                System.out.println("getAllClients Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public Client getClientById(int idc) {
        String query = "SELECT * FROM Clients "
                + "INNER JOIN ClientDetails ON Clients.idc = ClientDetails.idc "
                + "INNER JOIN LoginClient ON Clients.idc = LoginClient.idc "
                + "WHERE Clients.idc = ?";
        Connection conn = getConnection();
        Client client = null;
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idc);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getString("Clients.firstname");
                    String lastName = rs.getString("Clients.lastname");
                    String email = rs.getString("ClientDetails.email");
                    String city = rs.getString("ClientDetails.city");
                    String postCode = rs.getString("ClientDetails.postcode");
                    int houseNumber = rs.getInt("ClientDetails.housenumber");
                    Date dob = rs.getDate("ClientDetails.dob");
                    boolean blocked = rs.getString("LoginClient.blocked").charAt(0) == 'T';
                    boolean disable = rs.getString("Clients.disable").charAt(0) == 'T';
                    String userName = rs.getString("LoginClient.login");
                    String street = rs.getString("ClientDetails.street");

                    client = new Client(idc, lastName, firstName, email, street,
                            houseNumber, postCode, userName, disable, blocked, dob, city);
                }
            } catch (SQLException ex) {
                System.out.println("getClientById Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return client;
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
            } catch (SQLException ex) {
                System.out.println("getClientLogins Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("addClientIntoDatabase Error: " + ex.toString());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    private boolean insertIntoClients(String firstName, String lastName, Connection conn) {
        String query = "INSERT INTO Clients (firstname, lastname) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            boolean isUpdate = ps.executeUpdate() == 1;
            return isUpdate;

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
            boolean isUpdate = ps.executeUpdate() == 1;
            return isUpdate;
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
            boolean isUpdate = ps.executeUpdate() == 1;
            return isUpdate;
        } catch (SQLException ex) {
            System.out.println("insertIntoLoginClient Error: " + ex.toString());
        }
        return false;
    }

    public List<Account> getClientAccounts(int idc) {
        String query = "SELECT * FROM Accounts WHERE idc LIKE ?";
        Connection conn = getConnection();
        List<Account> listOfAccounts = new ArrayList<Account>();
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idc);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Account account = new Account(rs.getLong("idacc"), idc, rs.getFloat("balance"));
                    listOfAccounts.add(account);
                }

            } catch (SQLException ex) {
                System.out.println("getClientAccounts Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listOfAccounts;
    }

    public boolean addNewAccount(int idc, long accNum) {
        String query = "INSERT INTO Accounts(idc, idacc, balance) VALUES(?,?,0)";
        Connection conn = getConnection();
        boolean isUpdate = false;
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, idc);
                ps.setLong(2, accNum);
                isUpdate = ps.executeUpdate() == 1;
            } catch (SQLException ex) {
                System.out.println("addNewAccount Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return isUpdate;
    }

    public List<Long> getAllAccountNumbers() {
        String query = "SELECT idacc FROM Accounts";
        Connection conn = getConnection();
        List<Long> accountNumbersList = new ArrayList<Long>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    accountNumbersList.add(rs.getLong("idacc"));
                }
            } catch (SQLException ex) {
                System.out.println("getAllAccountNumbers Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return accountNumbersList;
    }

    public boolean updateClientInfo(Client client) {
        String queryClients = "UPDATE Clients SET firstname = ?"
                + ", lastname = ? WHERE idc = ?";
        String queryClientDetails = "UPDATE ClientDetails SET street = ?,"
                + " housenumber = ?, postcode = ?, dob = ?, email = ?, city = ?"
                + " WHERE idc = ?";
        boolean isUpdate = false;
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);

                PreparedStatement psClients = conn.prepareStatement(queryClients);
                PreparedStatement psClientDetails = conn.prepareStatement(queryClientDetails);

                psClients.setString(1, client.getFirstName());
                psClients.setString(2, client.getLastName());
                psClients.setInt(3, client.getIdc());

                psClientDetails.setString(1, client.getStreet());
                psClientDetails.setInt(2, client.getHouseNumber());
                psClientDetails.setString(3, client.getPostCode());
                psClientDetails.setDate(4, (java.sql.Date) client.getDob());
                psClientDetails.setString(5, client.getEmail());
                psClientDetails.setString(6, client.getCity());
                psClientDetails.setInt(7, client.getIdc());
                if (psClients.executeUpdate() == 1 && psClientDetails.executeUpdate() == 1) {
                    isUpdate = true;
                    conn.commit();
                } else {
                    conn.rollback();
                }

            } catch (SQLException ex) {
                try {
                    conn.rollback();
                    System.out.println("upDateClientInfo Error: " + ex.toString());
                } catch (SQLException ex1) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return isUpdate;
    }

    private boolean updateAccountBalance(long idacc, float amount, Connection conn) throws SQLException {
        String query = "UPDATE Accounts SET balance = balance + ? WHERE idacc = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setFloat(1, amount);
        ps.setLong(2, idacc);
        return ps.executeUpdate() == 1;
    }

    public float getAccountBalance(long idacc) {
        String query = "SELECT balance FROM Accounts WHERE idacc = ?";
        Connection conn = getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, idacc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat("balance");
            }
        } catch (SQLException ex) {
            System.out.println("getAccountBalance Error: " + ex.toString());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    private boolean logCashTransaction(int idemp, long idacc, float amount, Connection conn) throws SQLException {
        if (!(idemp <= 0 || amount == 0 || idemp == 0)) {
            String query = "INSERT INTO CashTransactions(idemp, idacc, amount) "
                    + "VALUES(?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idemp);
            ps.setLong(2, idacc);
            ps.setFloat(3, amount);
            return ps.executeUpdate() == 1;
        }
        return false;
    }

    public void addCashToClient(long idacc, int idemp, float amount) {
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            if (updateAccountBalance(idacc, amount, conn) && logCashTransaction(idemp, idacc, amount, conn)) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.out.println("addCashToClient Error: " + ex.toString());
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addNewCard(long cardnumber, long idacc, int pincode) {
        String query = "INSERT INTO BankCards(cardnumber, idacc, pincode) "
                + "VALUES(?,?,?)";
        Connection conn = getConnection();
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, cardnumber);
                ps.setLong(2, idacc);
                ps.setInt(3, pincode);
                return ps.executeUpdate() == 1;
            } catch (SQLException ex) {
                System.out.println("addNewCard Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public List<Long> getAllCardNumbers() {
        String query = "SELECT cardnumber FROM BankCards";
        Connection conn = getConnection();
        List<Long> cardNumbersList = new ArrayList<Long>();
        if (conn != null) {
            try (Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    cardNumbersList.add(rs.getLong("cardnumber"));
                }
            } catch (SQLException ex) {
                System.out.println("getAllCardNumbers Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cardNumbersList;
    }

    public List<Card> getClientCards(long idacc) {
        String query = "SELECT * FROM BankCards WHERE idacc LIKE ?";
        Connection conn = getConnection();
        List<Card> listOfCards = new ArrayList<>();
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, idacc);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int idCard = rs.getInt("idcard");
                    long cardNumber = rs.getLong("cardnumber");
                    boolean blocked = rs.getString("blocked").charAt(0) == 'T';
                    int pinCode = rs.getInt("pincode");
                    Card card = new Card(idCard, cardNumber, idacc, blocked, pinCode);
                    listOfCards.add(card);
                }

            } catch (SQLException ex) {
                System.out.println("getClientCards Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listOfCards;
    }

    public boolean updateClientCard(Card card) {
        String query = "UPDATE BankCards SET pincode = ?, blocked = ? WHERE idcard = ?";
        Connection conn = getConnection();
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, card.getPincode());
                ps.setString(2, card.isBlocked() ? "T" : "F");
                ps.setInt(3, card.getIdCard());
                return ps.executeUpdate() == 1;
            } catch (SQLException ex) {
                System.out.println("updateClientCard Error: " + ex.toString());
            }
        }
        return false;
    }

    public void performBankTransaction(long srcacc, long destacc, int srcbank,
            int destbank, float amount, int idemp, String description) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
                if (destbank == 2701) {

                    if (updateAccountBalance(srcacc, -1 * amount, conn)
                            && updateAccountBalance(destacc, amount, conn)
                            && logBankTransaction(conn, idemp, amount, srcacc,
                                    destacc, srcbank, destbank, description)) {
                        System.out.println("done");
                        conn.commit();
                    } else {
                        System.out.println("not done");
                        conn.rollback();
                    }

                } else {
                    if (updateAccountBalance(srcacc, -1 * amount, conn)
                            && logBankTransaction(conn, idemp, amount, srcacc,
                                    destacc, srcbank, destbank, description)) {
                        conn.commit();
                    } else {
                        conn.rollback();
                    }
                }

            } catch (SQLException ex) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex1);
                }
                System.out.println("performBankTransaction Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private boolean logBankTransaction(Connection conn, int idemp, float amount,
            long srcacc, long destacc, int srcbank, int destbank, String description) {

        String query = "INSERT INTO BankTransactions"
                + "(idemp, amount, srcacc, destacc, srcbank, destbank, description) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idemp);
            ps.setFloat(2, Math.abs(amount));
            ps.setLong(3, srcacc);
            ps.setLong(4, destacc);
            ps.setInt(5, srcbank);
            ps.setInt(6, destbank);
            ps.setString(7, description);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("logBankTransaction Error: " + ex.toString());
            return false;
        }
    }

    public List<BankTransaction> getBankTransactions(long idacc) {
        String query = "SELECT amount, transdatetime, idemp, destacc FROM "
                + "BankTransactions WHERE srcacc = ?";
        List<BankTransaction> transactions = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, idacc);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BankTransaction transaction = new BankTransaction(
                            rs.getFloat("amount"),
                            rs.getDate("transdatetime"),
                            rs.getInt("idemp"),
                            rs.getLong("destacc")
                    );
                    transactions.add(transaction);
                }
            } catch (SQLException ex) {
                System.out.println("getBankTransactions Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return transactions;
    }

    public List<CashTransaction> getCashTransactions(long idacc) {
        String query = "SELECT amount, cashdatetime, idemp, idacc FROM "
                + "CashTransactions WHERE idacc = ?";
        List<CashTransaction> transactions = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setLong(1, idacc);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    CashTransaction transaction = new CashTransaction(
                            rs.getInt("idemp"),
                            rs.getFloat("amount"),
                            idacc,
                            rs.getDate("cashdatetime")
                    );
                    transactions.add(transaction);
                }
            } catch (SQLException ex) {
                System.out.println("getCashTransactions Error: " + ex.toString());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return transactions;
    }

}
