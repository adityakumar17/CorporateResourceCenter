/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package minorproject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;


import javax.swing.JFrame;

/**
 *
 * @author adity
 */
public class CompaniesHome extends javax.swing.JFrame {

    /**
     * Creates new form StudentHome
     */
    public CompaniesHome() {
        initComponents();
        displayCompanyData();
    }
    
    private void displayCompanyData() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);  // Clear existing data

    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM crc.company WHERE CompanyId <> 0");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Object[] row = new Object[4];  // Assuming there are 4 columns in your table
            row[0] = resultSet.getObject("CompanyID");  // Replace "Column1" with the actual column name
            row[1] = resultSet.getObject("CompanyName");  // Replace "Column2" with the actual column name
            row[2] = resultSet.getObject("Email");  // Replace "Column3" with the actual column name
            row[3] = resultSet.getObject("Location");  // Replace "Column4" with the actual column name
            model.addRow(row);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
    }
}
    
    private void deleteCompanyById(String companyIdToDelete) {
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
        String deleteQuery = "DELETE FROM crc.company WHERE CompanyId = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, companyIdToDelete);
            
            int rowsDeleted = preparedStatement.executeUpdate();
            
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Company with CompanyId " + companyIdToDelete + " deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                displayCompanyData();  // Refresh the displayed data after deletion
            } else {
                JOptionPane.showMessageDialog(this, "No company found with CompanyId " + companyIdToDelete, "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        delete = new javax.swing.JButton();
        jTextFieldCompanyId = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        Location = new javax.swing.JTextField();
        Update = new javax.swing.JButton();
        CLEAR = new javax.swing.JButton();
        add = new javax.swing.JButton();
        home = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Company ID", "Company Name", "Email", "Location"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jTextFieldCompanyId.setText("ID");
        jTextFieldCompanyId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCompanyIdActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldCompanyId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 100, -1));
        jPanel1.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 150, -1));
        jPanel1.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 150, -1));
        jPanel1.add(Location, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 150, -1));

        Update.setText("UPDATE");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        jPanel1.add(Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, -1));

        CLEAR.setText("CLEAR");
        CLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARActionPerformed(evt);
            }
        });
        jPanel1.add(CLEAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, -1, -1));

        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel1.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 70, -1));

        home.setText("HOME");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, -1, -1));

        logout.setText("LOGOUT");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Company Home.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        String companyIdToDelete = jTextFieldCompanyId.getText().trim();
    
    if (!companyIdToDelete.isEmpty()) {
        deleteCompanyById(companyIdToDelete);
    } else {
        JOptionPane.showMessageDialog(this, "Please enter a CompanyId.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void jTextFieldCompanyIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCompanyIdActionPerformed
        // TODO add your handling code here:
        // Get the CompanyId entered by the user
    String companyIdToRetrieve = jTextFieldCompanyId.getText().trim();

    // Check if the CompanyId is not empty
    if (!companyIdToRetrieve.isEmpty()) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

            // Retrieve company details based on CompanyId
            String selectQuery = "SELECT CompanyName, Email, Location FROM crc.company WHERE CompanyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, companyIdToRetrieve);
                
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if the result set is not empty
                if (resultSet.next()) {
                    // Display retrieved details in corresponding text fields
                    Name.setText(resultSet.getString("CompanyName"));
                    Email.setText(resultSet.getString("Email"));
                    Location.setText(resultSet.getString("Location"));
                } else {
                    // If no company found, display a message
                    JOptionPane.showMessageDialog(this, "No company found with CompanyId " + companyIdToRetrieve, "Not Found", JOptionPane.INFORMATION_MESSAGE);
                }

                resultSet.close();
            }

            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please enter a CompanyId.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jTextFieldCompanyIdActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        // Get the updated details from the text fields
    String updatedName = Name.getText().trim();
    String updatedEmail = Email.getText().trim();
    String updatedLocation = Location.getText().trim();
    String companyIdToUpdate = jTextFieldCompanyId.getText().trim();

    // Check if the CompanyId is not empty
    if (!companyIdToUpdate.isEmpty()) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

            // Update company details based on CompanyId
            String updateQuery = "UPDATE crc.company SET CompanyName = ?, Email = ?, Location = ? WHERE CompanyId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, updatedName);
                preparedStatement.setString(2, updatedEmail);
                preparedStatement.setString(3, updatedLocation);
                preparedStatement.setString(4, companyIdToUpdate);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Company with CompanyId " + companyIdToUpdate + " updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayCompanyData();  // Refresh the displayed data after updating
                } else {
                    JOptionPane.showMessageDialog(this, "No company found with CompanyId " + companyIdToUpdate, "Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please enter a CompanyId.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_UpdateActionPerformed

    private void CLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARActionPerformed
        // TODO add your handling code here:
        // Clear the text fields
        Name.setText("");
        Email.setText("");
        Location.setText("");
        jTextFieldCompanyId.setText("ID");
    }//GEN-LAST:event_CLEARActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        // Get values from the text fields
    String companyName = Name.getText().trim();
    String email = Email.getText().trim();
    String location = Location.getText().trim();

    // Check if the required fields are not empty
    if (!companyName.isEmpty() && !email.isEmpty() && !location.isEmpty()) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

            // Insert values into the database
            String insertQuery = "INSERT INTO crc.company (CompanyName, Email, Location) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, companyName);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, location);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Company added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayCompanyData();  // Refresh the displayed data after adding
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add company.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        this.dispose();
        JFrame homeFrame = new Home();
        homeFrame.setVisible(true);
    }//GEN-LAST:event_homeActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        JFrame LoginFrame = new Login();
        LoginFrame.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

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
            java.util.logging.Logger.getLogger(CompaniesHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompaniesHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompaniesHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompaniesHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompaniesHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CLEAR;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Location;
    private javax.swing.JTextField Name;
    private javax.swing.JButton Update;
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCompanyId;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables
}
