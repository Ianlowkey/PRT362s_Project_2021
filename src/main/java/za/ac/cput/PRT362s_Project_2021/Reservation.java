        package za.ac.cput.PRT362s_Project_2021;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.*;
        import java.util.Vector;

        public class Reservation extends JFrame{


            Connection con = null;
            Statement st = null;
            ResultSet rs = null; String s;

            private JPanel mainPanel;
            private JPanel headerPanel;
            private JTextField timeTextfieldTextField;
            private JTextField nameTextfield;
            private JTextField nopplTextfield;
            private JTextField dateTextfield;
            private JButton RESETButton;
            private JButton CREATEButton;
            private JButton SHOWBOOKINGSButton;


            public Reservation() {

                super("Create Reservation");
                this.setContentPane(this.mainPanel);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.pack();

                CREATEButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameTextfield.getText();
                        String num_people = nopplTextfield.getText();
                        String booking_time = timeTextfieldTextField.getText();
                        String date = dateTextfield.getText();

                        try {

                            Class.forName("com.mysql.jdbc.Driver");
                            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","root",null);


                            String sql = "INSERT INTO reservation_list " + "VALUES ('"+name+"' ,'"+ num_people+"','"+ booking_time+"','"+ date+"')";

                           /* String createTableSQL = "CREATE TABLE reservation_list " +
                                    "(name VARCHAR(255), " +
                                    " numPeople VARCHAR(255), " +
                                    " time VARCHAR(255), " +
                                    " date VARCHAR(255), " ;*/

                            Statement statement = connect.createStatement();
                            //statement.executeUpdate(createTableSQL);
                            statement.executeUpdate(sql);


                            JOptionPane.showMessageDialog(null, "Booking created!");

                            connect.close();


                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });

                RESETButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nameTextfield.setText("");
                        nopplTextfield.setText("");
                        timeTextfieldTextField.setText("");
                        dateTextfield.setText("");
                    }
                });
                SHOWBOOKINGSButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       // createTable();
                        ReservationList reservationList = new ReservationList();
                        reservationList.show();
                        dispose();
                    }

                });
            }



            private void createUIComponents() {
                // TODO: place custom component creation code here
                //createTable();
            }

            private void createTable(){


                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false","root","");
                    st = con.createStatement();
                    s = "select * from reservation_list";
                    rs = st.executeQuery(s);
                    ResultSetMetaData rsmt = rs.getMetaData();
                    int c = rsmt.getColumnCount();
                    Vector column = new Vector(c);

                    for(int i = 1; i <= c; i++) {
                        column.add(rsmt.getColumnName(i));
                    }

                    Vector data = new Vector();
                    Vector row = new Vector();

                    while(rs.next()) {
                        row = new Vector(c);

                        for(int i = 1; i <= c; i++){
                            row.add(rs.getString(i));
                        }

                        data.add(row);
                    }

                    JFrame frame = new JFrame();
                    frame.setSize(500,120);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JPanel panel = new JPanel();
                    JTable table = new JTable(data,column);
                    JScrollPane jsp = new JScrollPane(table);
                    panel.setLayout(new BorderLayout());
                    panel.add(jsp,BorderLayout.CENTER);
                    frame.setContentPane(panel); frame.setVisible(true);

                }
                    catch(Exception e)
                {
                        JOptionPane.showMessageDialog(null, "ERROR");
                }
                finally{
                    try{
                        st.close();
                        rs.close();
                        con.close();
                    }
                    catch(Exception e){ JOptionPane.showMessageDialog(null, "ERROR CLOSE");
                    }
                }
            }


            public static void main(String[] args) {
                JFrame frame = new Reservation();
                frame.setTitle("Reservation");
                frame.setSize(500, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }


        }


