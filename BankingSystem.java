import java.sql.*;

public class BankingSystem {

    private Connection connection;

    public BankingSystem() 
    {
        connection = DBConnection.connect();
    }

    // Method to create a new account
    public boolean createAccount(String accountNumber, String name, double initialBalance) 
    {
        try 
        {
            String query = "INSERT INTO accounts (account_number, name, balance) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, accountNumber);
            stmt.setString(2, name);
            stmt.setDouble(3, initialBalance);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    //Method to deposit money
    public boolean depositMoney(String accountNumber, double amount) 
    {
        try 
        {
            String query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setString(2, accountNumber);
            return stmt.executeUpdate() > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    //Method to withdraw money
    public boolean withdrawMoney(String accountNumber, double amount) 
    {
        try 
        {
            String query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? AND balance >= ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setString(2, accountNumber);
            stmt.setDouble(3, amount);  // Make sure there's enough balance
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Method to check balance
    public double checkBalance(String accountNumber) 
    {
        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    //Method to log transaction
    public boolean logTransaction(String accountNumber, String transactionType, double amount) 
    {
        try 
        {
            String query = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, accountNumber);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}
