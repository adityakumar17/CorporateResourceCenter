
package minorproject;

import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JFrame;

public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
    }
        // TODO add your handling code here:

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        login_button = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        user_name = new javax.swing.JTextField();
        adminlogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        login_button.setText("login");
        login_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_buttonActionPerformed(evt);
            }
        });
        jPanel1.add(login_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 150, -1));

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 150, -1));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 182, -1));

        user_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_nameActionPerformed(evt);
            }
        });
        jPanel1.add(user_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 182, -1));

        adminlogin.setText("Admin");
        adminlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminloginActionPerformed(evt);
            }
        });
        jPanel1.add(adminlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 150, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Login Page.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void login_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_buttonActionPerformed
         if (user_name.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(null, "Please fill the user name");
        } 
         else if (password.getText().equals("")) 
        {
        JOptionPane.showMessageDialog(null, "Please fill the password");
        } 
        else 
        {
        var id = user_name.getText();
        var pass = password.getText();
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from usercredentials where user='" + id + "' and pass='" + pass + "'");
            if (rs.next()) 
            {
                JOptionPane.showMessageDialog(null, "Welcome, " + id);
                this.dispose();
                JFrame homeFrame = new Home();
                homeFrame.setVisible(true);
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Login Error", JOptionPane.WARNING_MESSAGE);
            }
        } 
        catch (Exception e) 
            {
            System.out.print(e);
            }
        }   
    }//GEN-LAST:event_login_buttonActionPerformed

    private void user_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nameActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_cancelActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void adminloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminloginActionPerformed
        // TODO add your handling code here:
        this.dispose();
                JFrame adminFrame = new adminlogin();
                adminFrame.setVisible(true);
    }//GEN-LAST:event_adminloginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminlogin;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login_button;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField user_name;
    // End of variables declaration//GEN-END:variables
}
