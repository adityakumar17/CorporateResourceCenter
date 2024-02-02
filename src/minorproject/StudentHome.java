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
public class StudentHome extends javax.swing.JFrame {
    

    /**
     * Creates new form StudentHome
     */
    public StudentHome() {
        initComponents();
        displayOrSearchStudents("");
    }
   
    
        private void displayOrSearchStudents(String erpidToSearch) {
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
                preparedStatement = connection.prepareStatement("SELECT ERPID,Name,Course,Batch,MobileNumber,EmailAddress FROM student");
            } else {
                preparedStatement = connection.prepareStatement("SELECT ERPID,Name,Course,Batch,MobileNumber,EmailAddress FROM student WHERE ERPID = ?");
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
            preparedStatement = connection.prepareStatement("SELECT * FROM student");
        } else {
            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE ERPID = ?");
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
        LOGOUT = new javax.swing.JButton();
        DEL = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        HOME = new javax.swing.JButton();
        UPDATE = new javax.swing.JButton();
        ADD = new javax.swing.JButton();
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
        jPanel1.add(ERPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 160, -1));

        LOGOUT.setText("LOGOUT");
        LOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOUTActionPerformed(evt);
            }
        });
        jPanel1.add(LOGOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 90, -1));

        DEL.setText("DELETE");
        DEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELActionPerformed(evt);
            }
        });
        jPanel1.add(DEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 90, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 920, 420));

        HOME.setText("HOME");
        HOME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HOMEActionPerformed(evt);
            }
        });
        jPanel1.add(HOME, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 90, -1));

        UPDATE.setText("UPDATE");
        UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEActionPerformed(evt);
            }
        });
        jPanel1.add(UPDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 90, -1));

        ADD.setText("ADD NEW");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });
        jPanel1.add(ADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/student Home.png"))); // NOI18N
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

    private void DELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELActionPerformed
                                     
   // Get the ERPID to be deleted from jTextField1
    String erpidToDelete = ERPID.getText();

    // Check if the ERPID is empty
    if (erpidToDelete.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an ERPID.", "Input Error", JOptionPane.WARNING_MESSAGE);
        return; // Stop execution if ERPID is empty
    }

    // Create a database connection and delete the record
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE ERPID = ?");
        preparedStatement.setString(1, erpidToDelete);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Record with ERPID " + erpidToDelete + " has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No record found for ERPID: " + erpidToDelete, "No Data Found", JOptionPane.INFORMATION_MESSAGE);
        }

        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
    }

    // After deletion, you may want to refresh the displayed data
    displayOrSearchStudents("");
    }//GEN-LAST:event_DELActionPerformed

    private void ERPIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ERPIDActionPerformed
        // TODO add your handling code here:
        // Get the ERPID entered by the user in jTextField1
    String erpidToSearch = ERPID.getText();

    // Call the function to display or search students
    displayOrSearchStudents(erpidToSearch);
    }//GEN-LAST:event_ERPIDActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        // TODO add your handling code here:
        this.dispose();
        JFrame StudentAddFrame = new AddStudent();
        StudentAddFrame.setVisible(true);
    }//GEN-LAST:event_ADDActionPerformed

    private void HOMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HOMEActionPerformed
        // TODO add your handling code here:
        this.dispose();
                JFrame homeFrame = new Home();
                homeFrame.setVisible(true);
    }//GEN-LAST:event_HOMEActionPerformed

    private void LOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOUTActionPerformed
        // TODO add your handling code here:
        this.dispose();
                JFrame LoginFrame = new Login();
                LoginFrame.setVisible(true);
    }//GEN-LAST:event_LOGOUTActionPerformed

    private void UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEActionPerformed
        // TODO add your handling code here:
         this.dispose();
        JFrame updatestudentFrame = new UpdateStudent();
        updatestudentFrame.setVisible(true);
    }//GEN-LAST:event_UPDATEActionPerformed

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
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JButton DEL;
    private javax.swing.JTextField ERPID;
    private javax.swing.JButton HOME;
    private javax.swing.JButton LOGOUT;
    private javax.swing.JButton UPDATE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
