import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {

        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card No.");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn c = new Conn();  // Initialize connection to database
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();


            if (cardnumber.equals("") || pinnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Card Number and PIN cannot be empty");
                return;
            }

            if (c.s != null) {  // Ensure the Statement object is properly initialized
                String query = "SELECT * FROM login WHERE cardnumber = '" + cardnumber + "' AND pin = '" + pinnumber + "'";
                try {
                    ResultSet rs = c.s.executeQuery(query);

                    if (rs.next()) {
                        // User login successful, now check if PIN exists in the bank table
                        String checkPinQuery = "SELECT * FROM bank WHERE pin = '" + pinnumber + "'";
                        ResultSet rsBank = c.s.executeQuery(checkPinQuery);

                        if (!rsBank.next()) {
                            // If no record exists in the bank table for this PIN, insert it with a balance of 0
                            String insertBankQuery = "INSERT INTO bank (pin, balance) VALUES ('" + pinnumber + "', 0)";
                            c.s.executeUpdate(insertBankQuery);
                            System.out.println("New bank account created for PIN: " + pinnumber);
                        }

                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Database connection failed.");
            }

        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }


    public static void main(String args[]) {
        new Login();
    }
}
