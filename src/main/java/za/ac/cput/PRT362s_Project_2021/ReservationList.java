    package za.ac.cput.PRT362s_Project_2021;

    import net.proteanit.sql.DbUtils;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.*;

    import java.awt.BorderLayout;
    import javax.swing.*;
    import java.sql.*;
    import java.util.Vector;

    public class ReservationList extends JFrame{

        private JPanel mainPanel;
        private JTextField txtFieldName;
        private JTextField txtFieldNo;
        private JTextField txtFieldTime;
        private JTextField txtFieldDate;
        private JButton UPDATEButton;
        private JTable table1;
        private JButton RESETButton;
        private JButton BACKButton;
        private JButton SEARCHButton;
        private JTextField txtID;
        private JTable jtbl;

        public static void main(String[] args) {
            JFrame frame = new JFrame("ReservationList");
            frame.setContentPane(new ReservationList().mainPanel);
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
                pst = con.prepareStatement("select * from reservation_list");
                ResultSet rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        public ReservationList() {
            connect();
            table_load();
            UPDATEButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name, noppl, time,date;
                    name = txtFieldName.getText();
                    noppl = txtFieldNo.getText();
                    time = txtFieldTime.getText();
                    date = txtFieldDate.getText();
                    try {
                        pst = con.prepareStatement("update reservation_list set name = ?,num_people = ?,booking_time = ?, date = ? where name = ?");
                        pst.setString(1, name);
                        pst.setString(2, noppl);
                        pst.setString(3, time);
                        pst.setString(4, date);
                        pst.setString(5, name);

                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Booking Updated");
                        table_load();
                        txtFieldName.setText("");
                        txtFieldNo.setText("");
                        txtFieldDate.setText("");
                        txtFieldTime.setText("");
                        txtFieldName.requestFocus();
                    }

                    catch (SQLException e1)
                    {
                        e1.printStackTrace();
                    }
                }

            });
            SEARCHButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String name = txtID.getText();

                        pst = con.prepareStatement("select name ,num_people,booking_time,date from reservation_list where name = ?");
                        pst.setString(1, name);
                        ResultSet rs = pst.executeQuery();

                        if(rs.next()==true)
                        {
                            String fName = rs.getString(1);
                            String fNum = rs.getString(2);
                            String fTime = rs.getString(3);
                            String fDate = rs.getString(4);

                            txtFieldName.setText(fName);
                            txtFieldNo.setText(fNum);
                            txtFieldTime.setText(fTime);
                            txtFieldDate.setText(fDate);

                        }
                        else
                        {
                            txtFieldName.setText("");
                            txtFieldNo.setText("");
                            txtFieldTime.setText("");
                            txtFieldDate.setText("");
                            JOptionPane.showMessageDialog(null,"Booking cannot be found");

                        }
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }

            });
            BACKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Reservation reservation = new Reservation();
                    reservation.show();
                    dispose();
                }
            });
            RESETButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtFieldDate.setText("");
                    txtFieldTime.setText("");
                    txtFieldNo.setText("");
                    txtFieldName.setText("");
                    txtID.setText("");
                }
            });
        }
    }

