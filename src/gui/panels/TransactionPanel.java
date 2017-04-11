/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import glbank.Account;
import glbank.database.ConnectionProvider;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Martin
 */
public class TransactionPanel extends javax.swing.JPanel {

    private int idc;
    private int idemp;

    /**
     * Creates new form TransactionPanel
     */
    public TransactionPanel(int idc, int idemp) {
        initComponents();
        this.idc = idc;
        this.idemp = idemp;
        initForm();
    }

    private void initForm() {
        showListOfSourceAccount();
    }

    private void showListOfSourceAccount() {
        comboSourceAccount.removeAllItems();
        List<Account> clientAccounts = new ConnectionProvider().getClientAccounts(idc);
        if (!clientAccounts.isEmpty()) {
            for (Account account : clientAccounts) {
                comboSourceAccount.addItem("" + account.getIdacc());
            }
        }
    }

    private float getInputAmount() {
        float value;
        try {
            value = Float.parseFloat(txtSourceAmount.getText());
        } catch (NumberFormatException ex) {
            value = 0;
        }
        return value = (float) Math.round(value * 100) / 100;
    }

    private boolean checkGLDestinationAccount() {
        List<Long> allAccountNumbers = new ConnectionProvider().getAllAccountNumbers();
        long destinationAcc;
        try {
            destinationAcc = Long.parseLong(txtDestinationAcc.getText());
        } catch (NumberFormatException ex) {
            return false;
        }
        for (long accNum : allAccountNumbers) {
            if (destinationAcc == accNum) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDestinationAccountFormat() {
        String destinationAcc = txtDestinationAcc.getText();
        if (destinationAcc.length() >= 8 && destinationAcc.length() <= 10) {
            try {
                long temp = Long.parseLong(destinationAcc);
            } catch (NumberFormatException ex) {
                return false;
            }
            return true;
        }
        return false;
    }

    private int getDestBank() {
        String selectedBank = (String) comboBankCode.getSelectedItem();
        switch (selectedBank) {
            case "GLbank":
                return 2701;
            case "TatraBanka":
                return 1100;
            case "mBank":
                return 8360;
            default:
                return 2701;
        }
    }

    private boolean isEveryFieldFilled() {
        if (!"".equals(txtDestinationAcc.getText().trim()) && !"".equals(txtSourceAmount.getText().trim())) {
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSubmitTransaction = new javax.swing.JButton();
        txtSourceAmount = new javax.swing.JTextField();
        txtDestinationAcc = new javax.swing.JTextField();
        comboBankCode = new javax.swing.JComboBox<>();
        comboSourceAccount = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Amount :");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Source account :");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Destination account :");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Bank :");

        btnSubmitTransaction.setText("Submit");
        btnSubmitTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitTransactionActionPerformed(evt);
            }
        });

        comboBankCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GLbank", "TatraBanka", "mBank" }));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Description :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboBankCode, javax.swing.GroupLayout.Alignment.LEADING, 0, 290, Short.MAX_VALUE)
                            .addComponent(txtDestinationAcc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSourceAmount)
                            .addComponent(comboSourceAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(btnSubmitTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboSourceAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSourceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDestinationAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboBankCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnSubmitTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitTransactionActionPerformed
        if (isEveryFieldFilled()) {
            long srcacc;
            long destacc;
            float amount;
            try {
                srcacc = Long.parseLong((String) comboSourceAccount.getSelectedItem());
                destacc = Long.parseLong(txtDestinationAcc.getText());
                amount = Math.abs(Float.parseFloat(txtSourceAmount.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Check your fields!");
                return;
            }
            if (amount > new ConnectionProvider().getAccountBalance(srcacc)) {
                JOptionPane.showMessageDialog(this, "Not enough money on source account!");
                return;
            }

            int srcbank = 2701;
            int destbank = getDestBank();
            String description = txtDescription.getText();
            if (comboBankCode.getSelectedIndex() == 0) {
                if (checkGLDestinationAccount() && checkDestinationAccountFormat()) {
                    System.out.println("btn submit performed");
                    new ConnectionProvider().performBankTransaction(srcacc, destacc,
                            srcbank, destbank, amount, idemp, description);
                    JOptionPane.showMessageDialog(this, "Transaction performed!");
                } else {
                    JOptionPane.showMessageDialog(this, "Bad format of destination account");
                }
            } else {
                if (checkDestinationAccountFormat()) {
                    new ConnectionProvider().performBankTransaction(srcacc, destacc,
                            srcbank, destbank, amount, idemp, description);
                    JOptionPane.showMessageDialog(this, "Transaction performed!");
                } else {
                    JOptionPane.showMessageDialog(this, "Bad format of destination account");
                }
            }
        }
    }//GEN-LAST:event_btnSubmitTransactionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmitTransaction;
    private javax.swing.JComboBox<String> comboBankCode;
    private javax.swing.JComboBox<String> comboSourceAccount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtDestinationAcc;
    private javax.swing.JTextField txtSourceAmount;
    // End of variables declaration//GEN-END:variables
}
