package za.ac.cput.PRT362s_Project_2021;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import java.sql.DriverManager;

public class InventoryManagement extends JFrame{
    private JPanel mainPanel;
    private JTable table1;
    private JPanel btnPanel;
    private JButton UPDATEButton;
    private JButton DELETEButton;

    public InventoryManagement() {

        super("Restaurant Management System");
        this.setContentPane(this.mainPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String s;



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
