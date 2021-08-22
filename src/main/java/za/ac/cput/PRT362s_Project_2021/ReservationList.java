    package za.ac.cput.PRT362s_Project_2021;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.*;

    import java.awt.BorderLayout;
    import javax.swing.*;
    import java.sql.*;
    import java.util.Vector;

    public class ReservationList{

        private JPanel mainPanel;
        private JTable jtbl;
        private JButton exitBtn;
        private void createUIComponents(){

        }
        public static void main(String[] args){
            Connection con = null;
            Statement st = null;
            ResultSet rs = null; String s;




            try{ con = DriverManager.getConnection("jdbc:mysql://localhost/testdb","root","");
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
                JPanel mainPanel = new JPanel();
                JTable jtbl = new JTable(data,column);
                JScrollPane jsp = new JScrollPane(jtbl);
                mainPanel.setLayout(new BorderLayout());
                mainPanel.add(jsp,BorderLayout.CENTER);
                frame.setContentPane(mainPanel); frame.setVisible(true);

            }catch(Exception e){ JOptionPane.showMessageDialog(null, "ERROR"); }
            finally{
                try{ st.close();
                    rs.close();
                    con.close();
                }
                catch(Exception e){ JOptionPane.showMessageDialog(null, "ERROR CLOSE");
                }
            }
        }
    }

