package za.ac.cput.PRT362s_Project_2021;

/*
 * Name: Ian Louw
 * Student Number: 216250773
 * App: This is the Point of Sales system for the restaurant Application.
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    private DefaultTableModel receiptModel;
    private JTextArea textArea;
    private FileWriter writer;
    private JList lstItems;
    private JTable tblReceipt;
    private JTable newReceiptTable;
    private JButton btnDelete;
    private JButton btnSubTotal;
    private JButton btnDeleteOrder;
    private JScrollPane scrollPane;
    private JTable tblItems;
    private JScrollPane scrollItems;
    private JButton btnAdd;
    private JButton btnPayment;
    private Font font = new Font("Arial", Font.BOLD, 14);


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

        // --------- customizing buttons ------------//

        btnBreakfast.setFont(font);
        btnBreakfast.setForeground(Color.WHITE);
        btnBreakfast.setBackground(Color.red);
        btnMains.setFont(font);
        btnMains.setForeground(Color.white);
        btnMains.setBackground(Color.red);
        btnBurger.setFont(font);
        btnBurger.setForeground(Color.white);
        btnBurger.setBackground(Color.red);
        btnSides.setFont(font);
        btnSides.setForeground(Color.white);
        btnSides.setBackground(Color.red);
        btnDessert.setFont(font);
        btnDessert.setForeground(Color.white);
        btnDessert.setBackground(Color.red);
        btnBeverage.setFont(font);
        btnBeverage.setForeground(Color.white);
        btnBeverage.setBackground(Color.red);
        btnDeleteOrder.setFont(font);
        btnDeleteOrder.setForeground(Color.white);
        btnDeleteOrder.setBackground(Color.red);
        btnDelete.setFont(font);
        btnDelete.setForeground(Color.white);
        btnDelete.setBackground(Color.red);
        btnAdd.setFont(font);
        btnAdd.setForeground(Color.white);
        btnAdd.setBackground(new Color(153, 153, 255));
        btnSubTotal.setFont(font);
        btnSubTotal.setForeground(Color.WHITE);
        btnSubTotal.setBackground(new Color(153, 153, 255));
        btnPayment.setFont(font);
        btnPayment.setForeground(Color.WHITE);
        btnPayment.setBackground(new Color(153, 153, 255));


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

        receiptModel = new DefaultTableModel();
        newReceiptTable = new JTable(receiptModel);
        receiptModel.addColumn("Item Name");
        receiptModel.addColumn("Price");
    }

    public void buttonActions() {


        String url = "jdbc:mysql://localhost:3306/mysql";
        String user = "root";

        btnBreakfast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    DefaultTableModel itemDTM = new DefaultTableModel();
                    Object[] column = {"Item ID", "Item Name", "Price"};
                    itemDTM.setColumnIdentifiers(column);
                    tblItems.setModel(itemDTM);


            // Display list for breakfast items
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


            //Display list for mains items
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


                //Display list for sides items
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


            //Display list for burger items
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


            //Display list for mains items
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

            //Display list for mains items
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
                DefaultTableModel click2 = (DefaultTableModel) tblReceipt.getModel();


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
                double finalTotal = 0;
                double test1 = 0;
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
                    subTotal = (discount / totalpay) * 100;
                    finalTotal = totalpay - subTotal;

                    txtSubTotal.setText(Double.toString(finalTotal));
                }
            }
        });

        btnPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                DefaultTableModel selectAllModel = (DefaultTableModel) newReceiptTable.getModel();



                int reply = JOptionPane.showConfirmDialog(null,
                        "Is Payment Confirmed ?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);


                        if (reply == JOptionPane.YES_OPTION) {


                            selectRows(tblReceipt, 0, tblReceipt.getRowCount());
                            ArrayList<String> selectedItemValues = getSelectedItems(tblReceipt);


                            for (String string : selectedItemValues) {
                                System.out.println(string);
                                String total = txtSubTotal.getText();

                            }


                            //---------------Writing ArrayList (Receipt) to txt File------------------//
                            try {
                                FileWriter writer = new FileWriter("Receipt.txt",true);
                                BufferedWriter outStream = new BufferedWriter(writer);
                                String total = txtSubTotal.getText();


                                outStream.write("\n" + "------ Items ------" + "\n");
                                for (int a =0; a < selectedItemValues.size(); a++) {
                                    String str = "--    " + selectedItemValues.get(a) + "\n";

                                    outStream.write(str);
                                    }
                                outStream.write("------ Payment Pricing ------" + "\n");
                                outStream.write("Total: R" + total);
                                outStream.close();
                                writer.close();
                                }catch(IOException ex){

                            }

                            JOptionPane.showMessageDialog(null, "Payment is confimed \n Enjoy your day");

                            tblReceipt.revalidate();
                            clearTable();
                            txtTotal.setText("");
                            txtDiscount.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Please try again.");
                        }


            }
        });

    }

    public void tableUpdate(){

        tblReceipt.getModel();
    }

    //----------------------Calculation to work out total------------------//
    public void total() {

        double totalpay = 0;
            for (int i = 0; i < tblReceipt.getRowCount(); i++) {
                double sum = Double.parseDouble(tblReceipt.getValueAt(i, 2).toString());
                totalpay = sum + totalpay;
            }
            txtTotal.setText(Double.toString(totalpay));
    }

    //------------------------Clear Receipt Table---------------------//
    public void clearTable(){

        int rowCount = tblReceipt.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }


    //------------------Selection Mode and Selection Interval for ArrayList-----------------------//
    public void selectRows(JTable tblReceipt, int start, int end) {

        tblReceipt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        tblReceipt.setRowSelectionAllowed(true);

        tblReceipt.setRowSelectionInterval(0, tblReceipt.getModel().getRowCount() -1);


    }

    //----------------------- ArrayList To Store TblReceipt Rows------------------------//
    private ArrayList<String> getSelectedItems(JTable tblReceipt){
        ArrayList<String> selectedItemValues = new ArrayList<>();
        Object[] rows = new Object[2];

        int[] selectedRow = tblReceipt.getSelectedRows();
        int[] selectedCol = tblReceipt.getSelectedColumns();
        for (int i = 0; i < selectedRow.length; i++){
            for (int x = 0; x < tblReceipt.getColumnCount() - 2 ; x++){
                //selectedItemValues.add(String.valueOf(tblReceipt.getValueAt(i, x)));
                selectedItemValues.add(String.valueOf(tblReceipt.getValueAt(i,1)) + " " +  " R" + String.valueOf(tblReceipt.getValueAt(i, 2)));
            }
        }
        return selectedItemValues;
    }


}

