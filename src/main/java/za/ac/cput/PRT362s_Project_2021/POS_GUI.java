package za.ac.cput.PRT362s_Project_2021;

/*
 * Name: Ian Louw
 * Student Number: 216250773
 * App: This is the Point of Sales system for the restaurant Application.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class POS_GUI extends JFrame {
    private JFrame frame = new JFrame();
    private JPanel pnlMain;
    private JPanel pnlNorth;
    private JLabel lblHead;
    private JPanel pnlWest;
    private JPanel pnlW;
    private JButton btnBeverage;
    private JButton btnDessert;
    private JButton btnSides;
    private JButton btnMains;
    private JButton btnBurger;
    private JPanel pnlSouth;
    private JButton btnBreakfast;
    private JLabel lblTotal;
    private JTextField txtTotal;
    private JLabel lblDiscount;
    private JTextField txtDiscount;
    private JLabel lblSubTotal;
    private JTextField txtSubTotal;
    private JPanel pnlCenter;
    private JPanel pnlEast;
    private DefaultTableModel model;
    private JList lstItems;
    private JTable tblReceipt;
    private JButton btnDelete;
    private JButton btnSubTotal;
    private JButton btnDeleteOrder;
    private JScrollPane scrollPane;
    private JTable tblItems;
    private JScrollPane scrollItems;
    private JButton btnAdd;
    private JButton btnPayment;


    public static void main(String[] args) {
        POS_GUI pos = new POS_GUI();
        pos.setTitle("Restaurant Management System");
        pos.pack();
        pos.setSize(1200, 700);
        pos.setVisible(true);
    }
    public POS_GUI (){

        //frame.setContentPane(pnlMain);
        add(pnlMain);
        buttonActions();
        total();


    }

    public void  createUIComponents  (){


        String[] itemColumn = {"Item ID", "Item Name", "Price"};
        Object[][] itemData = {{null, null, null}};

        tblItems = new JTable(itemData, itemColumn);
        scrollItems = new JScrollPane(tblItems);
        add(scrollItems);

        model = new DefaultTableModel();
        tblReceipt = new JTable(model);
        model.addColumn("Item ID");
        model.addColumn("Item Name");
        model.addColumn("Price");
        //model.addColumn("#");
        //model.addColumn("#");


    }

    public void buttonActions() {



        btnBreakfast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    DefaultTableModel itemDTM = new DefaultTableModel();
                    Object[] column = {"Item ID", "Item Name", "Price"};
                    itemDTM.setColumnIdentifiers(column);
                    tblItems.setModel(itemDTM);

                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";

                try{
                    Connection myConn = DriverManager.getConnection(url, user, null);
                    Statement myStat = myConn.createStatement();
                    String sql = "SELECT * FROM project.breakfast";
                    ResultSet rs = myStat.executeQuery(sql);

                    while(rs.next()){

                        Object d[] = {rs.getInt("breakfastID"), rs.getString("itemName"), rs.getDouble("itemPrice")};
                        itemDTM.addRow(d);
                    }
                } catch (SQLException a){
                    a.printStackTrace();
                }

                }
            }
        );

        btnMains.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel itemDTM = new DefaultTableModel();
                Object[] column = {"Item ID", "Item Name", "Price"};
                itemDTM.setColumnIdentifiers(column);
                tblItems.setModel(itemDTM);

                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";

                try{
                    Connection myConn = DriverManager.getConnection(url, user, null);
                    Statement myStat = myConn.createStatement();
                    String sql = "SELECT * FROM project.mains";
                    ResultSet rs = myStat.executeQuery(sql);

                    while(rs.next()){

                        Object d[] = {rs.getInt("mainsID"), rs.getString("itemName"), rs.getDouble("itemPrice")};
                        itemDTM.addRow(d);
                    }
                } catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        btnSides.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel itemDTM = new DefaultTableModel();
                Object[] column = {"Item ID", "Item Name", "Price"};
                itemDTM.setColumnIdentifiers(column);
                tblItems.setModel(itemDTM);

                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";

                try{
                    Connection myConn = DriverManager.getConnection(url, user, null);
                    Statement myStat = myConn.createStatement();
                    String sql = "SELECT * FROM project.sides";
                    ResultSet rs = myStat.executeQuery(sql);

                    while(rs.next()){

                        Object d[] = {rs.getInt("sidesID"), rs.getString("itemName"), rs.getDouble("itemPrice")};
                        itemDTM.addRow(d);
                    }
                } catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        btnBurger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel itemDTM = new DefaultTableModel();
                Object[] column = {"Item ID", "Item Name", "Price"};
                itemDTM.setColumnIdentifiers(column);
                tblItems.setModel(itemDTM);

                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";

                try{
                    Connection myConn = DriverManager.getConnection(url, user, null);
                    Statement myStat = myConn.createStatement();
                    String sql = "SELECT * FROM project.burgers";
                    ResultSet rs = myStat.executeQuery(sql);

                    while(rs.next()){

                        Object d[] = {rs.getInt("burgersID"), rs.getString("itemName"), rs.getDouble("itemPrice")};
                        itemDTM.addRow(d);
                    }
                } catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        btnDessert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel itemDTM = new DefaultTableModel();
                Object[] column = {"Item ID", "Item Name", "Price"};
                itemDTM.setColumnIdentifiers(column);
                tblItems.setModel(itemDTM);


                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";

                try{
                    Connection myConn = DriverManager.getConnection(url, user, null);
                    Statement myStat = myConn.createStatement();
                    String sql = "SELECT * FROM project.dessert";
                    ResultSet rs = myStat.executeQuery(sql);

                    while(rs.next()){

                        Object d[] = {rs.getInt("dessertID"), rs.getString("itemName"), rs.getDouble("itemPrice")};
                        itemDTM.addRow(d);
                    }
                } catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        btnBeverage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel itemDTM = new DefaultTableModel();
                Object[] column = {"Item ID", "Item Name", "Price"};
                itemDTM.setColumnIdentifiers(column);
                tblItems.setModel(itemDTM);

                String url = "jdbc:mysql://localhost:3306/mysql";
                String user = "root";

                try{
                    Connection myConn = DriverManager.getConnection(url, user, null);
                    Statement myStat = myConn.createStatement();
                    String sql = "SELECT * FROM project.drinks";
                    ResultSet rs = myStat.executeQuery(sql);

                    while(rs.next()){

                        Object d[] = {rs.getInt("drinksID"), rs.getString("itemName"), rs.getDouble("itemPrice")};
                        itemDTM.addRow(d);
                    }
                } catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                TableModel click = tblItems.getModel();
                int indexs[] = tblItems.getSelectedRows();
                Object[] rows = new Object[3];

                //TableModel click2 = tblReceipt.getModel();
                DefaultTableModel click2 = (DefaultTableModel) tblReceipt.getModel();

                //Object[] column = {"#", "Item Name", "Price", "Quantity", "Total"};
                //model.setColumnIdentifiers(column);

                for (int i = 0; i < indexs.length; i++){

                    rows[0] = click.getValueAt(indexs[i], 0);
                    rows[1] = click.getValueAt(indexs[i], 1);
                    rows[2] = click.getValueAt(indexs[i], 2);


                    click2.addRow(rows);
                }
                tblReceipt.revalidate();
                total();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tblReceipt.getSelectedRow() != -1){
                    model.removeRow(tblReceipt.getSelectedRow());
                    total();
                }
            }
        });

        btnDeleteOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rowCount = tblReceipt.getRowCount();

                for(int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                    total();
                    txtDiscount.setText("");
                }
            }
        });

        btnSubTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int discount;
                double subTotal = 0;
                //discount = Integer.parseInt(txtDiscount.getText());
                double totalpay = Double.parseDouble(txtTotal.getText());
                //txtDiscount.setText("0");
                //int totalpay = 0;

                if (txtDiscount.getText().equals("")){

                    txtSubTotal.setText(Double.toString(totalpay));
                }

                else {

                    discount = Integer.parseInt(txtDiscount.getText());
                    //subTotal = (totalpay / 100) * discount;
                    subTotal = (discount * totalpay) / 100;

                    txtSubTotal.setText(Double.toString(subTotal));
                }
            }
        });

        btnPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String msg = "Payment Confirmed ?";

                int reply = JOptionPane.showConfirmDialog(null,
                        "Is Payment Confirmed ?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION){

                    JOptionPane.showMessageDialog(null, "Thank you for your support!");

                    clearTable();
                    txtTotal.setText("");
                    txtDiscount.setText("");
                    txtSubTotal.setText("");
                }

                else{
                    JOptionPane.showMessageDialog(null, "Please try again.");
                }

            }
        });

    }

    public void addRowtoTable(Object[] dataRow) {

        DefaultTableModel rDTM = new DefaultTableModel();
        Object[] column = {"#", "Item Name", "Price", "Quantity", "Total"};
        rDTM.setColumnIdentifiers(column);

        TableModel click = tblItems.getModel();
        int indexs[] = tblItems.getSelectedRows();
        Object[] row = new Object[2];
        for (int i = 0; i < indexs.length; i++){
                    row[0] = click.getValueAt(indexs[i], 0);
                    row[1] = click.getValueAt(indexs[i], 1);
        }
        rDTM.addRow(dataRow);
    }

    public void tableUpdate(){

        tblReceipt.getModel();
    }

    public void total() {

        double totalpay = 0;
            for (int i = 0; i < tblReceipt.getRowCount(); i++) {
                double sum = Double.parseDouble(tblReceipt.getValueAt(i, 2).toString());
                totalpay = sum + totalpay;
            }
            txtTotal.setText(Double.toString(totalpay));
    }

    public void clearTable(){

        int rowCount = tblReceipt.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

}
