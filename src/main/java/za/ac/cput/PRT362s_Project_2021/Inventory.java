package za.ac.cput.PRT362s_Project_2021;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class Inventory {
    private JPanel main;
    private JTextField txtItemName;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JButton SAVEButton;
    private JTable table1;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEARCHButton;
    private JTextField txtID;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory");
        frame.setContentPane(new Inventory().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");
            System.out.println("Successs");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    void table_load()
    {
        try
        {
            pst = con.prepareStatement("select * from inventory");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Inventory() {
        connect();
        table_load();
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName, price, quantity;
                itemName = txtItemName.getText();
                price = txtPrice.getText();
                quantity = txtQuantity.getText();

                try {
                    pst = con.prepareStatement("insert into inventory(food,count,price)values(?,?,?)");
                    pst.setString(1, itemName);
                    pst.setString(2, quantity);
                    pst.setString(3, price);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Item added");
                     table_load();
                    txtItemName.setText("");
                    txtPrice.setText("");
                    txtQuantity.setText("");
                    txtItemName.requestFocus();

                } catch (SQLException e1) {

                    e1.printStackTrace();
                }

            }

        });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
try{
                String foodName = txtID.getText();

                pst = con.prepareStatement("select food,count,price from inventory where food = ?");
                pst.setString(1, foodName);
                ResultSet rs = pst.executeQuery();

                if(rs.next()==true)
                {
                    String fName = rs.getString(1);
                    String fCount = rs.getString(2);
                    String fPrice = rs.getString(3);

                    txtItemName.setText(fName);
                    txtQuantity.setText(fCount);
                    txtPrice.setText(fPrice);

                }
                else
                {
                    txtItemName.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    JOptionPane.showMessageDialog(null,"Item cannot be found");

                }
            }
                catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName, price, quantity;
                itemName = txtItemName.getText();
                price = txtPrice.getText();
                quantity = txtQuantity.getText();
                try {
                    pst = con.prepareStatement("update inventory set food = ?,count = ?,price = ? where food = ?");
                    pst.setString(1, itemName);
                    pst.setString(2, quantity);
                    pst.setString(3, price);
                    pst.setString(4, itemName);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Item Updated");
                    table_load();
                    txtItemName.setText("");
                    txtPrice.setText("");
                    txtQuantity.setText("");
                    txtItemName.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }

        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String food;
                food = txtID.getText();

                try {
                    pst = con.prepareStatement("delete from inventory  where food = ?");

                    pst.setString(1, food);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Item Deleted!");
                    table_load();
                    txtItemName.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    txtID.setText("");
                    txtItemName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }

        });
    }
}
