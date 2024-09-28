import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdraw, back;
    String pinnumber;

    Withdrawl(String pinnumber) {
        
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon (ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 800);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 240, 400, 20);
        image.add(text);

        amount  = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 290, 320, 25);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 437, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 471, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == withdraw) {
            String number = amount.getText();
            Date date = new Date();

            if(number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount");
                return; // Exit early if amount is not entered
            }

            try {
                Conn c = new Conn();
                // Step 1: Retrieve the current balance
                String balanceQuery = "SELECT balance FROM bank WHERE pin = '" + pinnumber + "'";
                ResultSet rs = c.s.executeQuery(balanceQuery);

                if (rs.next()) {
                    int currentBalance = rs.getInt("balance");
                    int withdrawalAmount = Integer.parseInt(number);

                    // Step 2: Check if withdrawal amount is valid
                    if (withdrawalAmount > currentBalance) {
                        JOptionPane.showMessageDialog(null, "Insufficient balance!");
                        return; // Exit early if insufficient balance
                    }

                    // Step 3: Update the balance in the bank table
                    int newBalance = currentBalance - withdrawalAmount;
                    String updateBalanceQuery = "UPDATE bank SET balance = '" + newBalance + "' WHERE pin = '" + pinnumber + "'";
                    c.s.executeUpdate(updateBalanceQuery);

                    // Step 4: Insert the transaction record in the transactions table
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(date);
                    String transactionQuery = "INSERT INTO transactions (pin, date, type, amount, balance) VALUES('" + pinnumber + "', '" + formattedDate + "', 'Withdrawl', " + withdrawalAmount + ", " + newBalance + ")";
                    c.s.executeUpdate(transactionQuery);

                    // Step 5: Show success message
                    JOptionPane.showMessageDialog(null, "Rs " + withdrawalAmount + " Withdrawn Successfully\nNew Balance: Rs " + newBalance);
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "PIN not found!");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }


    public static void main(String args[]) {
        new Withdrawl("");
    }
}