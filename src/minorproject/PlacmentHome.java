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

/**
 *
 * @author adity
 */
public class PlacmentHome extends javax.swing.JFrame {

    /**
     * Creates new form PlacmentHome
     */
    public PlacmentHome() {
        initComponents();
        displayPlacementStatus("");
        
    }
    
    private void displayPlacementStatus(String erpidToSearch) {
    // Create a model for the JTable
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Clear the existing data in the JTable
    model.setRowCount(0);

    // If there are no columns, add them
    if (model.getColumnCount() == 0) {
        // Connect to the database and retrieve data
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
            PreparedStatement preparedStatement = null;

            if (erpidToSearch.isEmpty()) {
                preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId");
            } else {
                preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Student.ERPID = ?");
                preparedStatement.setString(1, erpidToSearch);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Continue with data retrieval and display
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
        PreparedStatement preparedStatement;

        if (erpidToSearch.isEmpty()) {
            preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId");
        } else {
            preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Student.ERPID = ?");
            preparedStatement.setString(1, erpidToSearch);
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the result set is empty
        if (!resultSet.isBeforeFirst()) {
            if (erpidToSearch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No student data found.", "No Data Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No data found for ERPID: " + erpidToSearch, "No Data Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Get data rows
            while (resultSet.next()) {
                Object[] row = new Object[model.getColumnCount()]; // Use the column count from the model
                for (int i = 1; i <= model.getColumnCount(); i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
    }
}

    
    private void displayPlacedStudents(String erpidToSearch) {
    // Create a model for the JTable
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Clear the existing data in the JTable
    model.setRowCount(0);

    // If there are no columns, add them
    if (model.getColumnCount() == 0) {
        // Connect to the database and retrieve data
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
            PreparedStatement preparedStatement = null;

            if (erpidToSearch.isEmpty()) {
                preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Placement.Status = 'Placed'");
            } else {
                preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Student.ERPID = ? AND Placement.Status = 'Placed'");
                preparedStatement.setString(1, erpidToSearch);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Continue with data retrieval and display
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
        PreparedStatement preparedStatement;

        if (erpidToSearch.isEmpty()) {
            preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Placement.Status = 'Placed'");
        } else {
            preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Student.ERPID = ? AND Placement.Status = 'Placed'");
            preparedStatement.setString(1, erpidToSearch);
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the result set is empty
        if (!resultSet.isBeforeFirst()) {
            if (erpidToSearch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No placed student data found.", "No Data Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No placed student found for ERPID: " + erpidToSearch, "No Data Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Get data rows
            while (resultSet.next()) {
                Object[] row = new Object[model.getColumnCount()]; // Use the column count from the model
                for (int i = 1; i <= model.getColumnCount(); i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
    }
}

    
   private void displayNotPlacedStudents(String erpidToSearch) {
    // Create a model for the JTable
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Clear the existing data in the JTable
    model.setRowCount(0);

    // If there are no columns, add them
    if (model.getColumnCount() == 0) {
        // Connect to the database and retrieve data
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
            PreparedStatement preparedStatement = null;

            if (erpidToSearch.isEmpty()) {
                preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Placement.Status = 'Not Placed'");
            } else {
                preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Student.ERPID = ? AND Placement.Status = 'Not Placed'");
                preparedStatement.setString(1, erpidToSearch);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add columns to the model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Continue with data retrieval and display
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
        PreparedStatement preparedStatement;

        if (erpidToSearch.isEmpty()) {
            preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Placement.Status = 'Not Placed'");
        } else {
            preparedStatement = connection.prepareStatement("SELECT Student.ERPID, Student.Name, Student.Course, Student.EmailAddress, Placement.Status AS Placement_Status, Company.CompanyName FROM Student JOIN Placement ON Student.PlacementStatus = Placement.PlacementId JOIN Company ON Company.CompanyId = student.CompanyId WHERE Student.ERPID = ? AND Placement.Status = 'Not Placed'");
            preparedStatement.setString(1, erpidToSearch);
        }

        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the result set is empty
        if (!resultSet.isBeforeFirst()) {
            if (erpidToSearch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No not placed student data found.", "No Data Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No not placed student found for ERPID: " + erpidToSearch, "No Data Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Get data rows
            while (resultSet.next()) {
                Object[] row = new Object[model.getColumnCount()]; // Use the column count from the model
                for (int i = 1; i <= model.getColumnCount(); i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
        }

        resultSet.close();
        preparedStatement.close();
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
        ERPID = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        home = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ERPID.setText("ERPID");
        ERPID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ERPIDActionPerformed(evt);
            }
        });
        jPanel1.add(ERPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 120, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Students", "Placed Students", "Not Placed Students" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(781, 120, 160, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ERPID", "Name", "Course", "Email Id", "Placment Status", "Company"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 940, -1));

        home.setText("HOME");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        logout.setText("LOGOUT");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Placement Home.png"))); // NOI18N
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

    private void ERPIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ERPIDActionPerformed
        // TODO add your handling code here:

        // Get the ERPID entered by the user in jTextField1
        String erpidToSearch = ERPID.getText();

        // Call the function to display or search students
        displayPlacementStatus(erpidToSearch);

    }//GEN-LAST:event_ERPIDActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        this.dispose();
        JFrame homeFrame = new Home();
        homeFrame.setVisible(true);
    }//GEN-LAST:event_homeActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        // Get the selected item from the combo box
    String selectedOption = jComboBox1.getSelectedItem().toString();

    // Call the appropriate display method based on the selected option
    switch (selectedOption) {
        case "All Students":
            displayPlacementStatus("");
            break;
        case "Placed Students":
            displayPlacedStudents("");
            break;
        case "Not Placed Students":
            displayNotPlacedStudents("");
            break;
        // Add more cases for additional options if needed
    }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
                JFrame LoginFrame = new Login();
                LoginFrame.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        JFrame UpdatePlacmentFrame = new UpdatePlacement();
        new UpdatePlacement().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(PlacmentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlacmentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlacmentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlacmentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlacmentHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ERPID;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logout;
    // End of variables declaration//GEN-END:variables
}
