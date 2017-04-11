/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import glbank.Account;
import glbank.Card;
import glbank.database.ConnectionProvider;
import gui.EditCardDialog;
import gui.ListOfTransactionsDialog;
import gui.NewAccountDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;

/**
 *
 * @author Martin
 */
public class ClientAccountsPanel extends javax.swing.JPanel {

    private int idc;
    private int idemp;
    private float value;
    private int selectedAccountIndex;
    private List<Account> accountsList;
    private List<Card> cardsList;
    private Card selectedCard;

    /**
     * Creates new form ClientAccountPanel
     */
    public ClientAccountsPanel(int idc, int idemp) {
        initComponents();
        this.idc = idc;
        this.idemp = idemp;
        showListOfAccounts();
        showListOfCards();
        lblBalanceValue.setText("");
        selectedAccountIndex = comboBoxAccountId.getSelectedIndex();
        showAccountBalance(selectedAccountIndex);
    }

    private void showListOfAccounts() {
        comboBoxAccountId.removeAllItems();
        accountsList = new ConnectionProvider().getClientAccounts(idc);
        if (accountsList != null) {
            for (Account account : accountsList) {
                comboBoxAccountId.addItem("" + account.getIdacc());
            }
            if (comboBoxAccountId.getItemCount() != 0) {
                comboBoxAccountId.setSelectedIndex(selectedAccountIndex);
            }
        }
    }

    private void showAccountBalance(int selectedAccount) {
        if (selectedAccount >= 0) {
            lblBalanceValue.setText("" + accountsList.get(selectedAccount).getBalance());
        }
    }

    private void updateBalance(int mark) {
        if (comboBoxAccountId.getItemCount() != 0 && !"".equals(txtAdditionalValue.getText())) {
            try {
                value = mark * Float.parseFloat(txtAdditionalValue.getText());
            } catch (Exception ex) {
                value = 0;
            }
            value = (float) Math.round(value * 100) / 100;
            if ((mark == -1 && accountsList.get(selectedAccountIndex).getBalance() > Math.abs(value))
                    || mark == 1) {
                long selectedAccountId = accountsList.get(selectedAccountIndex).getIdacc();

                //updating in database
                ConnectionProvider conn = new ConnectionProvider();
                conn.addCashToClient(selectedAccountId, idemp, value);
                accountsList.get(selectedAccountIndex).setBalance(conn.getAccountBalance(selectedAccountId));
                showAccountBalance(selectedAccountIndex);   
            }
        }
    }

    private long generateRandomAccountNumber() {
        boolean isUnique = false;
        long randomAccNum;
        List<Long> allAccounts = new ConnectionProvider().getAllAccountNumbers();
        do {
            randomAccNum = ThreadLocalRandom.current().nextLong(100000000, 900000000) * 11;
            if(allAccounts.isEmpty()){
                return randomAccNum;
            }
            for (long accountNumber : allAccounts) {
                if (randomAccNum == accountNumber) {
                    isUnique = false;
                    break;
                }
                isUnique = true;
            }
        } while (isUnique == false);

        return randomAccNum;
    }

    private long generateRandomCardNumber() {
        boolean isUnique = false;
        long randomCardNumber;
        List<Long> allCardNumbers = new ConnectionProvider().getAllCardNumbers();
        if (!allCardNumbers.isEmpty()) {
            do {
                randomCardNumber = ThreadLocalRandom.current().nextLong(100000000, 900000000) * 10000000;
                for (long cardNumber : allCardNumbers) {
                    if (randomCardNumber == cardNumber) {
                        isUnique = false;
                        break;
                    }
                    isUnique = true;
                }
            } while (isUnique == false);
        } else {
            randomCardNumber = ThreadLocalRandom.current().nextLong(100000000, 900000000) * 10000000;
        }
        return randomCardNumber;
    }

    private int generateRandomPin() {
        return ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
    }

    private void showListOfCards() {
        comboBoxCards.removeAllItems();
        if (!accountsList.isEmpty() && selectedAccountIndex != -1) {
            cardsList = new ConnectionProvider().getClientCards(accountsList.get(selectedAccountIndex).getIdacc());
            if (!cardsList.isEmpty()) {
                for (Card card : cardsList) {
                    String blocked = card.isBlocked() ? "[BLOCKED]" : "[ACTIVE]";
                    comboBoxCards.addItem("" + card.getCardNumber() + " " + blocked);
                }
            }
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

        jLabel1 = new javax.swing.JLabel();
        comboBoxAccountId = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblBalanceValue = new javax.swing.JLabel();
        txtAdditionalValue = new javax.swing.JTextField();
        btnSubMoney = new javax.swing.JButton();
        btnAddMoney = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnAddNewAccount = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        comboBoxCards = new javax.swing.JComboBox<>();
        btnEditCard = new javax.swing.JButton();
        btnAddCard = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnShowAllTransactions = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Account id :");

        comboBoxAccountId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAccountIdActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Balance :");

        lblBalanceValue.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblBalanceValue.setText("value");

        btnSubMoney.setText("Sub -");
        btnSubMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubMoneyActionPerformed(evt);
            }
        });

        btnAddMoney.setText("Add +");
        btnAddMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMoneyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Cash transaction");

        btnAddNewAccount.setText("Add new account");
        btnAddNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewAccountActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Cards :");

        comboBoxCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCardsActionPerformed(evt);
            }
        });

        btnEditCard.setText("Edit card");
        btnEditCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCardActionPerformed(evt);
            }
        });

        btnAddCard.setText("Add new card");
        btnAddCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCardActionPerformed(evt);
            }
        });

        btnShowAllTransactions.setText("Show all transactions");
        btnShowAllTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllTransactionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblBalanceValue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddCard)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditCard))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxAccountId, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxCards, 0, 246, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(469, 469, 469))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSubMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(btnAddMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnShowAllTransactions)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddNewAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtAdditionalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxAccountId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(comboBoxCards, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblBalanceValue)
                    .addComponent(btnEditCard)
                    .addComponent(btnAddCard))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtAdditionalValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubMoney)
                    .addComponent(btnAddMoney)
                    .addComponent(btnAddNewAccount)
                    .addComponent(btnShowAllTransactions))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxAccountIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAccountIdActionPerformed
        selectedAccountIndex = comboBoxAccountId.getSelectedIndex();
        showAccountBalance(selectedAccountIndex);
        showListOfCards();
    }//GEN-LAST:event_comboBoxAccountIdActionPerformed

    private void btnSubMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubMoneyActionPerformed
        updateBalance(-1);
        
    }//GEN-LAST:event_btnSubMoneyActionPerformed

    private void btnAddMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMoneyActionPerformed
        updateBalance(1);
    }//GEN-LAST:event_btnAddMoneyActionPerformed

    private void btnAddNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewAccountActionPerformed
        long randomAccNum = generateRandomAccountNumber();
        if (new ConnectionProvider().addNewAccount(idc, randomAccNum)) {
            showListOfAccounts();
            showAccountBalance(selectedAccountIndex);
            NewAccountDialog newAccDialog = new NewAccountDialog((JFrame) this.getRootPane().getParent(), true, randomAccNum);
            newAccDialog.setLocationRelativeTo(null);
            newAccDialog.setVisible(true);

        }
    }//GEN-LAST:event_btnAddNewAccountActionPerformed

    private void btnAddCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCardActionPerformed
        if (!accountsList.isEmpty()) {
            long idacc = accountsList.get(selectedAccountIndex).getIdacc();

            new ConnectionProvider().addNewCard(generateRandomCardNumber(),
                    idacc, generateRandomPin());
            showListOfCards();
        }
    }//GEN-LAST:event_btnAddCardActionPerformed

    private void btnEditCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCardActionPerformed
        if (selectedCard != null) {
            EditCardDialog editCard = new EditCardDialog((JFrame) this.getRootPane().getParent(), true, selectedCard);
            editCard.setLocationRelativeTo(null);
            editCard.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                try{
                    showListOfCards();
                }
                catch(Exception ex){
                    
                }
            }
        });
            editCard.setVisible(true);
        }
    }//GEN-LAST:event_btnEditCardActionPerformed

    private void comboBoxCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCardsActionPerformed
        if (comboBoxCards.getItemCount() != 0) {
            int index = comboBoxCards.getSelectedIndex();
            selectedCard = cardsList.get(index);
        } else {
            selectedCard = null;
        }
    }//GEN-LAST:event_comboBoxCardsActionPerformed

    private void btnShowAllTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllTransactionsActionPerformed
        ListOfTransactionsDialog transactionList = new ListOfTransactionsDialog((JFrame) 
                this.getRootPane().getParent(), true, accountsList.get(selectedAccountIndex).getIdacc());
        transactionList.setLocationRelativeTo(null);
        transactionList.setVisible(true);
    }//GEN-LAST:event_btnShowAllTransactionsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCard;
    private javax.swing.JButton btnAddMoney;
    private javax.swing.JButton btnAddNewAccount;
    private javax.swing.JButton btnEditCard;
    private javax.swing.JButton btnShowAllTransactions;
    private javax.swing.JButton btnSubMoney;
    private javax.swing.JComboBox<String> comboBoxAccountId;
    private javax.swing.JComboBox<String> comboBoxCards;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBalanceValue;
    private javax.swing.JTextField txtAdditionalValue;
    // End of variables declaration//GEN-END:variables
}
