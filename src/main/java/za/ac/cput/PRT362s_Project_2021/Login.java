package za.ac.cput.PRT362s_Project_2021;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JFrame {

    private JTextField textFieldUsername;
    private JPasswordField passwordFieldPassword;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton exitButton;
    private JButton registerButton;
    private JPanel headerPanel;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JLabel labelWelcome;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelExistingMember;
    private JLabel labelNewMember;
    private JPanel logoPanel;
    private JPanel mainPanel;

    public Login() {

        super("Restaurant Management System");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration?useSSL=false","root",null);

                    String username = textFieldUsername.getText();
                    String password = passwordFieldPassword.getText();

                    Statement statement = connect.createStatement();

                    String sql = "SELECT * FROM user_account WHERE Username = '"+username+"' and Password = '"+password+"'";

                    ResultSet result = statement.executeQuery(sql);

                    if(result.next()){
                        Dashboard dashboard = new Dashboard();
                        dashboard.show();
                        dispose();
                    }

                    else{
                      JOptionPane.showMessageDialog(null,"Username or Password is Invalid !!!","Oops!",JOptionPane.ERROR_MESSAGE);
                        textFieldUsername.setText("");
                        passwordFieldPassword.setText("");
                    }

                    connect.close();

                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldUsername.setText("");
                passwordFieldPassword.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRegistrationForm loginRegistrationForm = new LoginRegistrationForm();
                loginRegistrationForm.show();
                dispose();
            }
        });
    }
}
