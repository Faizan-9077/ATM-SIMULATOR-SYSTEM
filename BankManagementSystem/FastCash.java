import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton hundred, fivehundred, thousand, twothousand, fivethousand, tenthousand, back;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 800);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(210, 240, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        hundred = new JButton("Rs 100");
        hundred.setBounds(170, 367, 150, 30);
        hundred.setActionCommand("100");  // Setting the amount as action command
        hundred.addActionListener(this);
        image.add(hundred);

        fivehundred = new JButton("Rs 500");
        fivehundred.setBounds(355, 367, 150, 30);
        fivehundred.setActionCommand("500");
        fivehundred.addActionListener(this);
        image.add(fivehundred);

        thousand = new JButton("Rs 1000");
        thousand.setBounds(170, 402, 150, 30);
        thousand.setActionCommand("1000");
        thousand.addActionListener(this);
        image.add(thousand);

        twothousand = new JButton("Rs 2000");
        twothousand.setBounds(355, 402, 150, 30);
        twothousand.setActionCommand("2000");
        twothousand.addActionListener(this);
        image.add(twothousand);

        fivethousand = new JButton("Rs 5000");
        fivethousand.setBounds(170, 437, 150, 30);
        fivethousand.setActionCommand("5000");
        fivethousand.addActionListener(this);
        image.add(fivethousand);

        tenthousand = new JButton("Rs 10000");
        tenthousand.setBounds(355, 437, 150, 30);
        tenthousand.setActionCommand("10000");
        tenthousand.addActionListener(this);
        image.add(tenthousand);

        back = new JButton("BACK");
        back.setBounds(355, 471, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount = ae.getActionCommand();

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT balance FROM bank WHERE pin = '" + pinnumber + "'");
                if (rs.next()) {
                    int balance = rs.getInt("balance");

                    if (balance >= Integer.parseInt(amount)) {
                        int newBalance = balance - Integer.parseInt(amount);
                        String updateQuery = "UPDATE bank SET balance = '" + newBalance + "' WHERE pin = '" + pinnumber + "'";
                        c.s.executeUpdate(updateQuery);

                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = sdf.format(date);
                        String transactionQuery = "INSERT INTO transactions (pin, date, type, amount, balance) VALUES('" + pinnumber + "', '" + formattedDate + "', 'Withdrawal', '" + amount + "', '" + newBalance + "')";
                        c.s.executeUpdate(transactionQuery);

                        JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");
                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
