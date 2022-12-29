package GasStationSystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    public static void main(String[] args) {
        try{
            Class.forName("ohgfhgsgshhs");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/abcorg?autoReconnect=true&useSSL=false","root","kavindyaKoralegei27"
            ); //abcorg is our database name
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}


