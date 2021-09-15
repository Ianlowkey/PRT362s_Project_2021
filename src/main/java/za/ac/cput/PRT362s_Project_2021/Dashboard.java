package za.ac.cput.PRT362s_Project_2021;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    private JButton POSSystemButton;
    private JButton employeeManagementSystemButton;
    private JButton inventoryManagementSystemButton;
    private JButton tableReservationSystemButton;
    private JButton loyaltyRewardsSystemButton;
    private JButton logoutButton;
    private JLabel labelDashboard;
    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JPanel logoutPanel;
    private JPanel logoPanel;
    private JPanel mainPanel;

    public Dashboard() {

        super("Restaurant Management System");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        POSSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        employeeManagementSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeInformation employee = new EmployeeInformation();
               // employee.show();
                dispose();
            }
        });

        inventoryManagementSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        tableReservationSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reservation reserve = new Reservation();
              //  reserve.show();
                dispose();
            }
        });

        loyaltyRewardsSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerRewardsGui rewards = new CustomerRewardsGui();
                rewards.show();
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.show();
                dispose();

            }
        });
    }
}
