/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import glbank.BankTransaction;
import glbank.database.ConnectionProvider;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Martin
 */
public class ListOfTransactionsDialog extends javax.swing.JDialog {

    private long idacc;

    /**
     * Creates new form ListOfTransactionsDialog
     */
    public ListOfTransactionsDialog(java.awt.Frame parent, boolean modal, long idacc) {
        super(parent, modal);
        initComponents();
        this.idacc = idacc;
    }

    private void showListOfBankTransactions(DefaultTableModel model) {
        List<BankTransaction> bankTransactions = new ConnectionProvider().getBankTransactions(idacc);
        for (BankTransaction bankTransaction : bankTransactions) {
            model.addRow(new Object[]{
                bankTransaction.getTransDateTime(),
                bankTransaction.getAmount(),
                bankTransaction.getIdemp(),
                bankTransaction.getDestacc()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransactions = new javax.swing.JTable();
        comboTypeOfTransactions = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DateTime", "Amount", "Employee", "Destination"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTransactions);
        if (tableTransactions.getColumnModel().getColumnCount() > 0) {
            tableTransactions.getColumnModel().getColumn(0).setResizable(false);
            tableTransactions.getColumnModel().getColumn(1).setResizable(false);
            tableTransactions.getColumnModel().getColumn(2).setResizable(false);
            tableTransactions.getColumnModel().getColumn(3).setResizable(false);
        }

        comboTypeOfTransactions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose type :", "Bank Transactions", "Cash Transactions" }));
        comboTypeOfTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTypeOfTransactionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTypeOfTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(comboTypeOfTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboTypeOfTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTypeOfTransactionsActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableTransactions.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        if (comboTypeOfTransactions.getSelectedIndex() == 1) {
            showListOfBankTransactions(model);
        }
    }//GEN-LAST:event_comboTypeOfTransactionsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboTypeOfTransactions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTransactions;
    // End of variables declaration//GEN-END:variables
}