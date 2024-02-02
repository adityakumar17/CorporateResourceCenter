/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package minorproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

/**
 *
 * @author adity
 */
public class UpdatePlacement extends javax.swing.JFrame {

    
    /**
     * Creates new form UpdatePlacement
     */
    public UpdatePlacement() {
        initComponents();
        populatePlacementStatusComboBox();
        populateComboBox(); // Call method to populate the combo box on initialization
        addComboBoxListener(); // Add listener for JComboBox selection changes
    }
    
    private void addComboBoxListener() {
        // Listener for jComboBox1
    jComboBox1.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // When a new item is selected in jComboBox1, update jTextField6
                String selectedCompanyName = (String) jComboBox1.getSelectedItem();
                jTextField6.setText(selectedCompanyName);
            }
        }
    });

    // Listener for jComboBox2
    jComboBox2.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // When a new item is selected in jComboBox2, update jTextField5
                String selectedPlacementStatus = (String) jComboBox2.getSelectedItem();
                jTextField5.setText(selectedPlacementStatus);
            }
        }
    });
    }
    
     private void populateComboBox() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

            // Query to select company names from the company table
            String query = "SELECT CompanyName FROM company";

            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a list to store the company names
            List<String> companyNames = new ArrayList<>();

            // Iterate through the result set and add company names to the list
            while (resultSet.next()) {
                companyNames.add(resultSet.getString("CompanyName"));
            }

            // Populate the JComboBox with the company names
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(companyNames.toArray(new String[0])));

            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     // Add this method to your class
private void populatePlacementStatusComboBox() {
    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

        // Query to select placement statuses from the placement table
        String query = "SELECT Status FROM placement";

        // Create a prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Execute the query
        ResultSet resultSet = preparedStatement.executeQuery();

        // Create a list to store the placement statuses
        List<String> placementStatuses = new ArrayList<>();

        // Iterate through the result set and add placement statuses to the list
        while (resultSet.next()) {
            placementStatuses.add(resultSet.getString("Status"));
        }

        // Populate jComboBox2 with the placement statuses
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(placementStatuses.toArray(new String[0])));

        // Close the resources
        resultSet.close();
        preparedStatement.close();
        connection.close();

    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
    }
}

     
     private void fetchStudentDetails(String erpId) {
    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database (Replace with your actual database details)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

        // Query to select student details based on ERP ID
        String query = "SELECT "
                + "Student.Name, "
                + "Student.Course, "
                + "Student.EmailAddress, "
                + "Placement.Status AS Placement_Status, "
                + "Company.CompanyName "
                + "FROM Student "
                + "JOIN Placement ON Student.PlacementStatus = Placement.PlacementId "
                + "JOIN Company ON Company.CompanyId = Student.CompanyId "
                + "WHERE Student.ERPID = ?";

        // Create a prepared statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set the ERP ID parameter
            preparedStatement.setString(1, erpId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Populate the text fields with the retrieved data
                if (resultSet.next()) {
                    jTextField2.setText(resultSet.getString("Name"));
                    jTextField3.setText(resultSet.getString("Course"));
                    jTextField4.setText(resultSet.getString("EmailAddress"));
                    jTextField5.setText(resultSet.getString("Placement_Status"));
                    jTextField6.setText(resultSet.getString("CompanyName"));
                } else {
                    // Handle the case where no matching record is found
                    // You can display a message or clear the text fields
                    jTextField2.setText("Name");
                    jTextField3.setText("Course");
                    jTextField4.setText("Email");
                    jTextField5.setText("Placment Status");
                    jTextField6.setText("Company");
                     // Display a warning message
                    JOptionPane.showMessageDialog(this, "No data found for ERP ID: " + erpId, "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        // Close the connection (use try-with-resources to automatically close resources)
        connection.close();

    } catch (ClassNotFoundException | SQLException ex) {
        // Handle exceptions appropriately (consider logging or displaying an error message)
        ex.printStackTrace();
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

        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("ERPID");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 200, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 120, -1));

        jTextField2.setEditable(false);
        jTextField2.setText("Name");
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 200, -1));

        jTextField3.setEditable(false);
        jTextField3.setText("Course");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 200, -1));

        jTextField4.setEditable(false);
        jTextField4.setText("Email");
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 200, -1));

        jTextField5.setEditable(false);
        jTextField5.setText("Placment Status");
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 200, -1));

        jTextField6.setEditable(false);
        jTextField6.setText("Company");
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 200, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 120, -1));

        jButton2.setText("CANCEL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 80, -1));

        jButton3.setText("HOME");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 80, -1));

        jButton4.setText("LOGOUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 80, -1));

        jButton5.setText("SAVE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 80, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Placement Update.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        // Get the ERP ID from the text field
    String erpId = jTextField1.getText();

    // Call a method to fetch student details from the database based on the ERP ID
    fetchStudentDetails(erpId);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        JFrame PlacmentFrame = new PlacmentHome();
        new PlacmentHome().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:\
        this.dispose();
                JFrame homeFrame = new Home();
                homeFrame.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
                JFrame LoginFrame = new Login();
                LoginFrame.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         // Get the current value in jTextField5
    String placementStatus = jTextField5.getText();

    // Map placement status to corresponding codes
    int placementStatusCode;
    switch (placementStatus) {
        case "Placed":
            placementStatusCode = 2;
            break;
        case "Not Placed":
            placementStatusCode = 1;
            break;
        default:
            // Handle other cases or show an error message
            return;
    }

    // Get the ERP ID from jTextField1
    String erpId = jTextField1.getText();

    // Get the selected company name from jTextField6
    String selectedCompanyName = jTextField6.getText();

    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crc", "root", "1702");

        // Get the CompanyId for the selected company name
        int companyId = getCompanyId(connection, selectedCompanyName);

        if (companyId != -1) {
            // Update the PlacementStatus and CompanyId in the Student table
            String updateQuery = "UPDATE Student SET PlacementStatus = ?, CompanyId = ? WHERE ERPID = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, placementStatusCode);
                updateStatement.setInt(2, companyId);
                updateStatement.setString(3, erpId);

                // Execute the update query
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Update successful, you can show a success message if needed
                    JOptionPane.showMessageDialog(this, "PlacementStatus and CompanyId updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    JFrame PlacmentFrame = new PlacmentHome();
                    new PlacmentHome().setVisible(true);
                } else {
                    // Update failed, you can show a warning or error message
                    JOptionPane.showMessageDialog(this, "Failed to update PlacementStatus and CompanyId.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            // Handle the case where the selected company name is not found
            JOptionPane.showMessageDialog(this, "Selected company name not found in the Company table.", "Error", JOptionPane.WARNING_MESSAGE);

        }

        // Close the connection
        connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
        // Handle exceptions appropriately (consider logging or displaying an error message)
        ex.printStackTrace();
    }
}

// Helper method to get the CompanyId for a given company name
private int getCompanyId(Connection connection, String companyName) throws SQLException {
    String query = "SELECT CompanyId FROM Company WHERE CompanyName = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, companyName);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("CompanyId");
            } else {
                return -1; // Return -1 if the company name is not found
            }
        }
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    
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
            java.util.logging.Logger.getLogger(UpdatePlacement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePlacement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePlacement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePlacement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePlacement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
