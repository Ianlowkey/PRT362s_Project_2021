package za.ac.cput.PRT362s_Project_2021;
import za.ac.cput.PRT362s_Project_2021.Employee.Employee;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

class EmployeeInformation {
    JFrame mainframe;
    JPanel panel1,panel2,panel3,panel4;
    JTabbedPane tp;
    JLabel lblLogo,lblId, lblName, lblAddress, lblSalary,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12,tf13,tf14;
    JButton btnAdd,btnReset,btnEdit1,btnEdit2,btnDelete, btnSearch ;

    EmployeeInformation() {
        mainframe = new JFrame("Employee Application");
        panel1 = new JPanel(new GridLayout(5, 2));
        panel2 = new JPanel(new GridLayout(5, 2));
        panel3 = new JPanel(new GridLayout(2, 2));
        panel4 = new JPanel(new GridLayout(5, 1));
        tp = new JTabbedPane();
        lblId = new JLabel("Employee ID:");
        lblName = new JLabel("Employee Name:");
        lblAddress = new JLabel("Employee Address:");
        lblSalary = new JLabel("Salary:");
        lbl5 = new JLabel("Enter Employee ID to delete record:");

        lbl11 = new JLabel("Employee ID:");
        lbl12 = new JLabel("Employee Name:");
        lbl13 = new JLabel("Employee Address:");
        lbl14 = new JLabel("Salary:");

        lbl7 = new JLabel("Employee ID:");
        lbl8 = new JLabel("Employee Name:");
        lbl9 = new JLabel("Employee Address:");
        lbl10 = new JLabel("Salary:");
        tf1 = new JTextField(12);
        tf2 = new JTextField(12);
        tf3 = new JTextField(12);
        tf4 = new JTextField(12);
        tf5 = new JTextField(12);
        tf6 = new JTextField(12);
        tf7 = new JTextField(12);
        tf8 = new JTextField(12);
        tf9 = new JTextField(12);
        tf10 = new JTextField(12);
        tf11 = new JTextField(12);
        tf12 = new JTextField(12);
        tf13 = new JTextField(12);
        tf14 = new JTextField(12);
        btnAdd = new JButton(" Add ");
        btnReset = new JButton(" Reset");
        btnEdit1 = new JButton(" Update ");
        btnEdit2 = new JButton(" Save");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");


        panel1.setBackground(new Color(88, 220, 204));

        panel1.add(lblId);
        panel1.add(tf1);
        panel1.add(lblName);
        panel1.add(tf2);
        panel1.add(lblAddress);
        panel1.add(tf3);
        panel1.add(lblSalary);
        panel1.add(tf4);
        panel1.add(btnAdd);
        panel1.add(btnReset);

        panel2.add(lbl7);
        panel2.add(tf7);
        panel2.add(lbl8);
        panel2.add(tf8);
        panel2.add(lbl9);
        panel2.add(tf9);
        panel2.add(lbl10);
        panel2.add(tf10);
        panel2.add(btnEdit1);
        panel2.add(btnEdit2);


        panel4.add(lbl11);
        panel4.add(tf11);
        panel4.add(lbl12);
        panel4.add(tf12);
        panel4.add(lbl13);
        panel4.add(tf13);
        panel4.add(lbl14);
        panel4.add(tf14);
        panel4.add(btnSearch);


        panel3.add(lbl5);
        panel3.add(tf5);
        panel3.add(btnDelete);





        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String value1 = tf1.getText();
                String value2 = tf2.getText();
                String value3 = tf3.getText();
                String value4 = tf4.getText();
                String url = "jdbc:mysql://localhost:3306/employee-jdbc";
                String user = "root";
                String password = "P@ssword2019";
                int query;

                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Statement statement = connection.createStatement();
                    query = statement.executeUpdate("INSERT INTO employee VALUES('" + value1 + "', '" + value2 + "', '" + value3 + "', '" + value4 + "')");
                    if (query > 0) {
                        JOptionPane.showMessageDialog(null, "Data is successfully inserted into database.");
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error submitting data!.");
                        tf1.hasFocus();
                    }
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error:" + sqlException);
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String value1 = tf5.getText();
                String value2 = tf2.getText();
                String value3 = tf3.getText();
                String value4 = tf4.getText();
                String url = "jdbc:mysql://localhost:3306/employee-jdbc";
                String user = "root";
                String password = "P@ssword2019";
                Connection connection = null;
                Statement statement = null;
                int empID;
                try {
                    connection = DriverManager.getConnection(url ,user,password);
                    statement = connection.createStatement();
                    empID = statement.executeUpdate("DELETE FROM employee WHERE emp_id = '" +value1 + "'");
                    JOptionPane.showMessageDialog(panel3, "Record is deleted successfully.");
                   tf5.setText("");
                    tf5.requestFocus();
                    connection.close();
                } catch (Exception exp3) {
                    JOptionPane.showMessageDialog(panel3, "Error in deleting record.");
                }
            }
        });

    btnEdit1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae){


            String url = "jdbc:mysql://localhost:3306/employee-jdbc";
            String user = "root";
            String password = "P@ssword2019";
            Connection connection = null;



            try {
                connection = DriverManager.getConnection(url,user,password);
                String  query = "UPDATE employee SET emp_name =?, emp_address = ?, salary=? WHERE emp_id = ? ";
                PreparedStatement psr = connection.prepareStatement(query);


                psr.setString(1, tf8.getText());
                psr.setString(2, tf9.getText());
                psr.setString(3, tf10.getText());
                psr.setString(4,tf7.getText());
                psr.executeUpdate();

                JOptionPane.showMessageDialog(null,"Record Is Successfully Updated");

            }
            catch(SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error: " + sqlException);
            }
        }
    });
       btnEdit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                String url = "jdbc:mysql://localhost:3306/employee-jdbc";
                String user = "root";
                String password = "P@ssword2019";
                Connection con = null;
                try {
                    int x = JOptionPane.showConfirmDialog(panel2, "Confirm edit? All data will be replaced");
                    if (x == 0) {
                        try {
                            String value1 = tf7.getText();
                            String value2 = tf8.getText();
                            String value3 = tf9.getText();
                            String value4 = tf10.getText();

                            con = DriverManager.getConnection(url,user,password);
                            Statement st = con.createStatement();
                            st.executeUpdate("update employee set emp_name='" + value2 + "', emp_address='" + value3 + "', salary='" + value4 + "' where emp_id='" + value1 + "'");
                            JOptionPane.showMessageDialog(panel2, "Updated successfully");
                            con.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(panel2, "Error in updating edit fields");
                        }
                    }
                }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(panel2,"Error");
                    }
                }
            });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){

                String value1 = tf11.getText();
                String value2 = tf12.getText();
                String value3 = tf13.getText();
                String value4 = tf14.getText();
                String url = "jdbc:mysql://localhost:3306/employee-jdbc";
                String user = "root";
                String password = "P@ssword2019";
                Connection connection = null;
                ResultSet resultSet = null;


                                try {
                    connection = DriverManager.getConnection(url, user, password);
                    Statement statement = connection.createStatement();
                     resultSet = statement.executeQuery("SELECT * FROM employee WHERE emp_id = '" + value1 + "'");
                    if (resultSet.next()) {

                        tf12.setText(resultSet.getString("emp_name"));
                        tf13.setText(resultSet.getString("emp_address"));
                        tf14.setText(resultSet.getString("salary"));

                    }
                    else
                        JOptionPane.showMessageDialog(null,"Employee record not found");
                                }
                                catch(SQLException sqlException) {
                                    JOptionPane.showMessageDialog(null, "Error: " + sqlException);
                                }
            }
        });
    }




    void dis()
    {
        mainframe.getContentPane().add(tp);
        tp.addTab("Add Record",panel1);
        tp.addTab("Edit Record",panel2);
        tp.addTab("Delete Record",panel3);
        tp.addTab("Search",panel4);

        mainframe.setSize(450,280);
        mainframe.setVisible(true);
        mainframe.setResizable(true);
    }
    public static void main(String z[]){
        EmployeeInformation pro=new EmployeeInformation();
        pro.dis();
    }
}

