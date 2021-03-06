/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import glbank.database.ConnectionProvider;

/**
 *
 * @author Martin
 */
public class ChangePasswordDialog extends javax.swing.JDialog {

    private int idemp;

    public boolean isNewPasswordValid(String password) {
        password = password.trim();
        int passwordLength = password.length();
        boolean lowerCase = false;
        boolean upperCase = false;
        boolean number = false;
        boolean nonAlphanumeric = false;

        if (passwordLength > 5) {
            for (int i = 0; i < passwordLength; i++) {
                if (Character.isLowerCase(password.charAt(i))) {
                    lowerCase = true;
                }
                if (Character.isUpperCase(password.charAt(i))) {
                    upperCase = true;
                }
                if (Character.isDigit(password.charAt(i))) {
                    number = true;
                }
                if (!Character.isLetter(password.charAt(i))
                        && !Character.isDigit(password.charAt(i))) {
                    nonAlphanumeric = true;
                }
            }
        }
        return lowerCase && upperCase && number && nonAlphanumeric;
    }

    /**
     * Creates new form ChangePasswordDialog
     */
    public ChangePasswordDialog(java.awt.Frame parent, boolean modal, int idemp) {
        super(parent, modal);
        initComponents();
        this.idemp = idemp;
        lblMatchError.setVisible(false);
        lblOldPasswordError.setVisible(false);
        lblNewPasswordError.setVisible(false);
    }

    public ChangePasswordDialog() {

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
        btnCancel = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        txtOldPassword = new javax.swing.JPasswordField();
        txtNewPassword = new javax.swing.JPasswordField();
        txtNewPassword2 = new javax.swing.JPasswordField();
        lblMatchError = new javax.swing.JLabel();
        lblOldPasswordError = new javax.swing.JLabel();
        lblNewPasswordError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Old password:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("New password:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Confirm new password:");

        btnCancel.setBackground(new java.awt.Color(153, 153, 153));
        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnChangePassword.setBackground(new java.awt.Color(153, 153, 153));
        btnChangePassword.setForeground(new java.awt.Color(0, 0, 0));
        btnChangePassword.setText("Change password");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        lblMatchError.setForeground(new java.awt.Color(255, 51, 51));
        lblMatchError.setText("Passwords don't match");

        lblOldPasswordError.setForeground(new java.awt.Color(255, 51, 51));
        lblOldPasswordError.setText("Incorrect password");

        lblNewPasswordError.setForeground(new java.awt.Color(255, 51, 51));
        lblNewPasswordError.setText("Invalid password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(btnCancel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNewPasswordError)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOldPasswordError)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtOldPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnChangePassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMatchError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNewPassword2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNewPassword, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(56, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOldPasswordError)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNewPasswordError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMatchError)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChangePassword)
                    .addComponent(btnCancel))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        String oldPassword = new String(txtOldPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());
        String newPassword2 = new String(txtNewPassword2.getPassword());
        boolean samePasswords = newPassword.equals(newPassword2);
        boolean correctOldPassword = new ConnectionProvider().
                isEmployeePasswordValid(idemp, oldPassword);
        boolean passwordValid = isNewPasswordValid(newPassword);;

        if (!passwordValid) {
            lblNewPasswordError.setVisible(true);
        } else {
            lblNewPasswordError.setVisible(false);
        }
        if (samePasswords) {
            lblMatchError.setVisible(false);

        } else {
            lblMatchError.setVisible(true);
        }

        if (correctOldPassword) {
            lblOldPasswordError.setVisible(false);
        } else {
            lblOldPasswordError.setVisible(true);
        }

        if (correctOldPassword && samePasswords && passwordValid) {
            ConnectionProvider conn = new ConnectionProvider();
            conn.changePassword(idemp, newPassword);
            this.dispose();
        }
    }//GEN-LAST:event_btnChangePasswordActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMatchError;
    private javax.swing.JLabel lblNewPasswordError;
    private javax.swing.JLabel lblOldPasswordError;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JPasswordField txtNewPassword2;
    private javax.swing.JPasswordField txtOldPassword;
    // End of variables declaration//GEN-END:variables
}
