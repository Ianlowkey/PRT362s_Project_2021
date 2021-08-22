package za.ac.cput.PRT362s_Project_2021;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class LoginRegistrationForm extends JFrame{

    private JPanel mainPanel;
    private JButton cancelButton;
    private JButton exitButton;
    private JButton submitButton;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldUsername;
    private JPasswordField passwordFieldPassword;
    private JPasswordField passwordFieldConfirmPassword;
    private JLabel labelRegistrationForm;
    private JLabel labelFirstName;
    private JLabel labelLastName;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelConfirmPassword;
    private JPanel headerPanel;
    private JPanel fieldPanel;
    private JPanel buttonPanel;

    public LoginRegistrationForm() {

        super("Restaurant Management System");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldFirstName.setText("");
                textFieldLastName.setText("");
                textFieldUsername.setText("");
                passwordFieldPassword.setText("");
                passwordFieldConfirmPassword.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = textFieldFirstName.getText();
                String lastName = textFieldLastName.getText();
                String username = textFieldUsername.getText();
                String password = passwordFieldPassword.getText();
                String confirmedPassword = passwordFieldConfirmPassword.getText();

                try{

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration?useSSL=false","root",null);

                    String sql = "INSERT INTO user_account values ('"+firstName+"','"+lastName+"','"+username+"','"+password+"','"+confirmedPassword+"')";

                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null,"Welcome "+firstName+" your account was successful created!","Thank You!",JOptionPane.PLAIN_MESSAGE);

                    connect.close();

                    Login login = new Login();
                    login.show();
                    dispose();

                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
