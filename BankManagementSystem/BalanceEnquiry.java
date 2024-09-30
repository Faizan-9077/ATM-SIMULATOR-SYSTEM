import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 800);
        add(image);

        JLabel text = new JLabel("Your Available Balance is: Rs ");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(160, 300, 400, 35);
        image.add(text);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(385, 300, 400, 35);
        image.add(balanceLabel);

        Conn c = new Conn();
        try {
            String query = "SELECT balance FROM bank WHERE pin = '" + pinnumber + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                String balance = rs.getString("balance");
                balanceLabel.setText(balance);
            } else {
                balanceLabel.setText("Balance not found!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        back = new JButton("Back");
        back.setBounds(410, 475, 100, 25);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}