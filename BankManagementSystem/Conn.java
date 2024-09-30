import java.sql.*;

public class Conn {

    Connection c;
    Statement s;

    public Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bankmanagementsystem" , "root" , "Faizan@mysql00" );
            s=c.createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
