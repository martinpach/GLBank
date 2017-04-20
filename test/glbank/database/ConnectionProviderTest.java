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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class ConnectionProviderTest {
    
    public ConnectionProviderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isEmployeePasswordValid method, of class ConnectionProvider.
     */
    @Test
    public void testIsEmployeePasswordValid1() {
        System.out.println("isEmployeePasswordValid");
        String username = "martinpach";
        String password = "12345";
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = true;
        boolean result = instance.isEmployeePasswordValid(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testIsEmployeePasswordValid2() {
        System.out.println("isEmployeePasswordValid");
        String username = "MaRTinpach";
        String password = "12345";
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.isEmployeePasswordValid(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testIsEmployeePasswordValid3() {
        System.out.println("isEmployeePasswordValid");
        String password = "12345";
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = true;
        boolean result = instance.isEmployeePasswordValid(1, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    

    /**
     * Test of getEmployeeId method, of class ConnectionProvider.
     */
    @Test
    public void testGetEmployeeId() {
        System.out.println("getEmployeeId");
        String username = "martinpach";
        ConnectionProvider instance = new ConnectionProvider();
        int expResult = 1;
        int result = instance.getEmployeeId(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }


    /**
     * Test of getEmployee method, of class ConnectionProvider.
     */
    @Test
    public void testGetEmployee() {
        System.out.println("getEmployee");
        int id = 1;
        ConnectionProvider instance = new ConnectionProvider();
        Employee result = instance.getEmployee(id);
        assertEquals("Martin", result.getFirstName());
        assertEquals("Pach", result.getLastName());
        assertEquals(1, result.getIdemp());
        assertEquals("m.pach97@gmail.com", result.getEmail());
        assertEquals('C', result.getPosition());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isEmployeePasswordValid method, of class ConnectionProvider.
     */
    @Test
    public void testIsEmployeePasswordValid_String_String() {
        System.out.println("isEmployeePasswordValid");
        String username = "";
        String password = "";
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.isEmployeePasswordValid(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmployeePasswordValid method, of class ConnectionProvider.
     */
    @Test
    public void testIsEmployeePasswordValid_int_String() {
        System.out.println("isEmployeePasswordValid");
        int idemp = 0;
        String password = "";
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.isEmployeePasswordValid(idemp, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logEmployeeAccess method, of class ConnectionProvider.
     */
    @Test
    public void testLogEmployeeAccess() {
        System.out.println("logEmployeeAccess");
        int id = 0;
        ConnectionProvider instance = new ConnectionProvider();
        instance.logEmployeeAccess(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateTime method, of class ConnectionProvider.
     */
    @Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        ConnectionProvider instance = new ConnectionProvider();
        String expResult = "";
        String result = instance.getDateTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePassword method, of class ConnectionProvider.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        int idemp = 0;
        String newPassword = "";
        ConnectionProvider instance = new ConnectionProvider();
        instance.changePassword(idemp, newPassword);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllClients method, of class ConnectionProvider.
     */
    @Test
    public void testGetAllClients() {
        System.out.println("getAllClients");
        ConnectionProvider instance = new ConnectionProvider();
        List<Client> expResult = null;
        List<Client> result = instance.getAllClients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientById method, of class ConnectionProvider.
     */
    @Test
    public void testGetClientById() {
        System.out.println("getClientById");
        int idc = 0;
        ConnectionProvider instance = new ConnectionProvider();
        Client expResult = null;
        Client result = instance.getClientById(idc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientLogins method, of class ConnectionProvider.
     */
    @Test
    public void testGetClientLogins() {
        System.out.println("getClientLogins");
        ConnectionProvider instance = new ConnectionProvider();
        List<String> expResult = null;
        List<String> result = instance.getClientLogins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClientIntoDatabase method, of class ConnectionProvider.
     */
    @Test
    public void testAddClientIntoDatabase() {
        System.out.println("addClientIntoDatabase");
        Client client = null;
        String password = "";
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.addClientIntoDatabase(client, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientAccounts method, of class ConnectionProvider.
     */
    @Test
    public void testGetClientAccounts() {
        System.out.println("getClientAccounts");
        int idc = 0;
        ConnectionProvider instance = new ConnectionProvider();
        List<Account> expResult = null;
        List<Account> result = instance.getClientAccounts(idc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewAccount method, of class ConnectionProvider.
     */
    @Test
    public void testAddNewAccount() {
        System.out.println("addNewAccount");
        int idc = 0;
        long accNum = 0L;
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.addNewAccount(idc, accNum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAccountNumbers method, of class ConnectionProvider.
     */
    @Test
    public void testGetAllAccountNumbers() {
        System.out.println("getAllAccountNumbers");
        ConnectionProvider instance = new ConnectionProvider();
        List<Long> expResult = null;
        List<Long> result = instance.getAllAccountNumbers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClientInfo method, of class ConnectionProvider.
     */
    @Test
    public void testUpdateClientInfo() {
        System.out.println("updateClientInfo");
        Client client = null;
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.updateClientInfo(client);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountBalance method, of class ConnectionProvider.
     */
    @Test
    public void testGetAccountBalance() {
        System.out.println("getAccountBalance");
        long idacc = 0L;
        ConnectionProvider instance = new ConnectionProvider();
        float expResult = 0.0F;
        float result = instance.getAccountBalance(idacc);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCashToClient method, of class ConnectionProvider.
     */
    @Test
    public void testAddCashToClient() {
        System.out.println("addCashToClient");
        long idacc = 0L;
        int idemp = 0;
        float amount = 0.0F;
        ConnectionProvider instance = new ConnectionProvider();
        instance.addCashToClient(idacc, idemp, amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewCard method, of class ConnectionProvider.
     */
    @Test
    public void testAddNewCard() {
        System.out.println("addNewCard");
        long cardnumber = 0L;
        long idacc = 0L;
        int pincode = 0;
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.addNewCard(cardnumber, idacc, pincode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCardNumbers method, of class ConnectionProvider.
     */
    @Test
    public void testGetAllCardNumbers() {
        System.out.println("getAllCardNumbers");
        ConnectionProvider instance = new ConnectionProvider();
        List<Long> expResult = null;
        List<Long> result = instance.getAllCardNumbers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientCards method, of class ConnectionProvider.
     */
    @Test
    public void testGetClientCards() {
        System.out.println("getClientCards");
        long idacc = 0L;
        ConnectionProvider instance = new ConnectionProvider();
        List<Card> expResult = null;
        List<Card> result = instance.getClientCards(idacc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClientCard method, of class ConnectionProvider.
     */
    @Test
    public void testUpdateClientCard() {
        System.out.println("updateClientCard");
        Card card = null;
        ConnectionProvider instance = new ConnectionProvider();
        boolean expResult = false;
        boolean result = instance.updateClientCard(card);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performBankTransaction method, of class ConnectionProvider.
     */
    @Test
    public void testPerformBankTransaction() {
        System.out.println("performBankTransaction");
        long srcacc = 0L;
        long destacc = 0L;
        int srcbank = 0;
        int destbank = 0;
        float amount = 0.0F;
        int idemp = 0;
        String description = "";
        ConnectionProvider instance = new ConnectionProvider();
        instance.performBankTransaction(srcacc, destacc, srcbank, destbank, amount, idemp, description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBankTransactions method, of class ConnectionProvider.
     */
    @Test
    public void testGetBankTransactions() {
        System.out.println("getBankTransactions");
        long idacc = 0L;
        ConnectionProvider instance = new ConnectionProvider();
        List<BankTransaction> expResult = null;
        List<BankTransaction> result = instance.getBankTransactions(idacc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCashTransactions method, of class ConnectionProvider.
     */
    @Test
    public void testGetCashTransactions() {
        System.out.println("getCashTransactions");
        long idacc = 0L;
        ConnectionProvider instance = new ConnectionProvider();
        List<CashTransaction> expResult = null;
        List<CashTransaction> result = instance.getCashTransactions(idacc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
    
    
}
