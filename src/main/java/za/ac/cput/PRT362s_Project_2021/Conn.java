package za.ac.cput.PRT362s_Project_2021;

import java.sql.*;

public class Conn {
    Connection con ;
    Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/restaurant","root","");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
