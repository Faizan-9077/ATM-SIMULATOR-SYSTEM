import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 800);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 240, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 290, 320, 25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 437, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 471, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount");
            } else {
                try {
                    Conn c = new Conn();
                    String query1 = "SELECT balance FROM bank WHERE pin = '" + pinnumber + "'";
                    ResultSet rs = c.s.executeQuery(query1);

                    if (rs.next()) {
                        int balance = rs.getInt("balance");
                        int depositAmount = Integer.parseInt(number);

                        int newBalance = balance + depositAmount;

                        String query2 = "UPDATE bank SET balance = '" + newBalance + "' WHERE pin = '" + pinnumber + "'";
                        int updateCount = c.s.executeUpdate(query2);

                        if (updateCount > 0) {
                            System.out.println("Balance updated successfully. New balance: " + newBalance);
                        } else {
                            System.out.println("Balance update failed.");
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = sdf.format(date);

                        String query3 = "INSERT INTO transactions (pin, date, type, amount, balance) VALUES('" + pinnumber + "', '" + formattedDate + "', 'Deposit', " + depositAmount + ", " + newBalance + ")";
                        int insertCount = c.s.executeUpdate(query3);

                        if (insertCount > 0) {
                            JOptionPane.showMessageDialog(null, "Rs " + number + " Deposited Successfully");
                            setVisible(false);
                            new Transactions(pinnumber).setVisible(true);
                        } else {
                            System.out.println("Transaction insert failed.");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "PIN not found!");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Deposit("");
    }
}
