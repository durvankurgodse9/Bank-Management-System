import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection connect() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db", "root", "password");
        } catch (SQLException | ClassNotFoundException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
}
