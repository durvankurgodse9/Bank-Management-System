import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankingApp {
    
    private JFrame frame;
    private JTextField accountNumberField, nameField, amountField;
    private JButton createAccountButton, depositButton, withdrawButton, checkBalanceButton;
    private JLabel balanceLabel;

    private BankingSystem bankingSystem;

    public BankingApp() {
        bankingSystem = new BankingSystem();
        frame = new JFrame("Banking System");
        frame.setLayout(new FlowLayout());
        
        accountNumberField = new JTextField(20);
        nameField = new JTextField(20);
        amountField = new JTextField(20);
        createAccountButton = new JButton("Create Account");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");
        balanceLabel = new JLabel("Balance: $0");

        frame.add(new JLabel("Account Number:"));
        frame.add(accountNumberField);
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(createAccountButton);
        frame.add(new JLabel("Amount:"));
        frame.add(amountField);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(checkBalanceButton);
        frame.add(balanceLabel);

        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String name = nameField.getText();
                double initialBalance = Double.parseDouble(amountField.getText());
                bankingSystem.createAccount(accountNumber, name, initialBalance);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(amountField.getText());
                bankingSystem.depositMoney(accountNumber, amount);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(amountField.getText());
                bankingSystem.withdrawMoney(accountNumber, amount);
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double balance = bankingSystem.checkBalance(accountNumber);
                balanceLabel.setText("Balance: $" + balance);
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new BankingApp();
    }
}
