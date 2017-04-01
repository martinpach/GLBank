/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.panels.ClientInfoPanel;
import gui.panels.TransactionPanel;
import gui.panels.ClientAccountsPanel;
import glbank.Client;
import glbank.Employee;
import glbank.database.ConnectionProvider;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 *
 * @author Martin
 */
public class MainForm extends javax.swing.JFrame {

    private int idemp;
    private List<Client> list;
    private ConnectionProvider conn;

    /**
     * Creates new form MainForm
     */
    public MainForm(int idemp) {
        initComponents();
        this.idemp = idemp;
        conn = new ConnectionProvider();
        initForm();
    }

    public MainForm() {
        initComponents();
        showListOfClients();
    }

    private void initForm() {
        printEmployeeName();
        showListOfClients();
        initTabs();
    }

    private void printEmployeeName() {
        Employee employee = conn.getEmployee(idemp);
        if (employee != null) {
            String name = employee.getFirstName() + " " + employee.getLastName();
            lblLoggedEmployee.setText("Logged in user: " + name);
        }
    }

    private void showListOfClients() {
        comboListOfAllClients.removeAllItems();
        comboListOfAllClients.addItem("Choose:");
        list = new ConnectionProvider().getAllClients();
        if (list != null && list.size() > 0) {
            for (Client client : list) {
                String item = client.getLastName() + " " + client.getFirstName()
                        + " [" + client.getDob() + "]";
                comboListOfAllClients.addItem(item);

            }
        }
    }

    private void initTabs() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLoggedEmployee = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        comboListOfAllClients = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        btnNewClient = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuChangePassword = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        lblLoggedEmployee.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblLoggedEmployee.setText("Logged in user: FirstName LastName");

        jLabel1.setText("Select client :");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        comboListOfAllClients.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "choose:" }));
        comboListOfAllClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboListOfAllClientsActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        btnNewClient.setBackground(new java.awt.Color(153, 153, 153));
        btnNewClient.setForeground(new java.awt.Color(0, 0, 0));
        btnNewClient.setText("New client");
        btnNewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClientActionPerformed(evt);
            }
        });

        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));

        jMenu1.setText("Menu");

        menuChangePassword.setText("Change password");
        menuChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangePasswordActionPerformed(evt);
            }
        });
        jMenu1.add(menuChangePassword);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        menuAbout.setText("About");
        menuAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAboutMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(208, 807, Short.MAX_VALUE)
                .addComponent(lblLoggedEmployee)
                .addGap(42, 42, 42))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnNewClient, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboListOfAllClients, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblLoggedEmployee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboListOfAllClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewClient)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangePasswordActionPerformed
        ChangePasswordDialog changePasswordDialog
                = new ChangePasswordDialog(this, true, idemp);
        changePasswordDialog.setLocationRelativeTo(null);
        changePasswordDialog.setVisible(true);
    }//GEN-LAST:event_menuChangePasswordActionPerformed

    private void btnNewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClientActionPerformed
        NewClientDialog newClientDialog = new NewClientDialog(this, true);
        newClientDialog.setLocationRelativeTo(null);
        newClientDialog.setVisible(true);
        
        // after closing new client dialog the main form is refreshed
        newClientDialog.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                try{
                    initForm();
                }
                catch(Exception ex){
                    
                }
            }
        });
    }//GEN-LAST:event_btnNewClientActionPerformed

    private void menuAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAboutMouseClicked
        AboutDialog aboutDialog = new AboutDialog(this, true);
        aboutDialog.setLocationRelativeTo(null);
        aboutDialog.setVisible(true);
    }//GEN-LAST:event_menuAboutMouseClicked

    private void comboListOfAllClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboListOfAllClientsActionPerformed
        jTabbedPane1.removeAll();
        int index = comboListOfAllClients.getSelectedIndex();
        if (index > 0) {
            Client selectedClient = list.get(index - 1);
            Client client = new ConnectionProvider().getClientById(selectedClient.getIdc());
            ClientInfoPanel clientInfoPanel = new ClientInfoPanel(client);
            ClientAccountsPanel clientAccountsPanel = new ClientAccountsPanel(selectedClient.getIdc());
            TransactionPanel transactionPanel = new TransactionPanel();
            jTabbedPane1.add("Information", clientInfoPanel);
            jTabbedPane1.add("Accounts", clientAccountsPanel);
            jTabbedPane1.add("Transaction", transactionPanel);
        }
    }//GEN-LAST:event_comboListOfAllClientsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewClient;
    private javax.swing.JComboBox<String> comboListOfAllClients;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblLoggedEmployee;
    private javax.swing.JMenu menuAbout;
    private javax.swing.JMenuItem menuChangePassword;
    private javax.swing.JMenuItem menuExit;
    // End of variables declaration//GEN-END:variables
}
