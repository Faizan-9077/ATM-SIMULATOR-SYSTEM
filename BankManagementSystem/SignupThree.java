import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    JLabel number, pnumber;
    String formno, fullCardNumber, fullPIN;

    SignupThree(String formno) {
        this.formno = formno;

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 820);
        setLocation(350, 0);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 180, 200, 20);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 180, 250, 20);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 220, 200, 20);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 220, 250, 20);
        add(r4);

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        JLabel card = new JLabel("Account Number");
        card.setFont(new Font("Raleway", Font.BOLD, 22));
        card.setBounds(100, 300, 200, 30);
        add(card);

        fullCardNumber = generateCardNumber(); 
        number = new JLabel("XXXX-XXXX-XXXX-" + fullCardNumber.substring(12,16)); 
        number.setFont(new Font("Raleway", Font.BOLD, 22));
        number.setBounds(330, 300, 300, 30);
        add(number);

        JLabel carddetail = new JLabel("Your 16-Digit Account Number");
        carddetail.setFont(new Font("Raleway", Font.BOLD, 12));
        carddetail.setBounds(100, 330, 300, 20);
        add(carddetail);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100, 370, 200, 30);
        add(pin);

        fullPIN = generatePIN(); 
        pnumber = new JLabel("XXXX"); 
        pnumber.setFont(new Font("Raleway", Font.BOLD, 22));
        pnumber.setBounds(330, 370, 300, 30);
        add(pnumber);

        JLabel pindetail = new JLabel("Your 4-Digit PIN");
        pindetail.setFont(new Font("Raleway", Font.BOLD, 12));
        pindetail.setBounds(100, 400, 300, 20);
        add(pindetail);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 450, 250, 30);
        add(services);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setOpaque(true);
        c1.setBounds(100, 500, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setOpaque(true);
        c2.setBounds(350, 500, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setOpaque(true);
        c3.setBounds(100, 550, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIL & SMS Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setOpaque(true);
        c4.setBounds(350, 550, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setOpaque(true);
        c5.setBounds(100, 600, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setOpaque(true);
        c6.setBounds(350, 600, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setOpaque(true);
        c7.setBounds(100, 680, 600, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 720, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 720, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
        repaint();
        revalidate();
        setUndecorated(true);
    }

    public static String generateCardNumber() {
        long first12Digits = ThreadLocalRandom.current().nextLong(100_000_000_000L, 1_000_000_000_000L);
        int last4Digits = ThreadLocalRandom.current().nextInt(1000, 10000);  
        return String.format("%012d%04d", first12Digits, last4Digits);  
    }

    public static String generatePIN() {
        int pin = ThreadLocalRandom.current().nextInt(1000, 10000);  
        return String.format("%04d", pin); 
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String accountType = null;
                if (r1.isSelected()) accountType = "Saving Account";
                else if (r2.isSelected()) accountType = "Fixed Deposit Account";
                else if (r3.isSelected()) accountType = "Current Account";
                else if (r4.isSelected()) accountType = "Recurring Deposit Account";

                if (accountType == null) {
                    throw new Exception("Account type is required.");
                }

                String services = "";
                if (c1.isSelected()) services += "ATM CARD, ";
                if (c2.isSelected()) services += "Internet Banking, ";
                if (c3.isSelected()) services += "Mobile Banking, ";
                if (c4.isSelected()) services += "EMAIL & SMS Alerts, ";
                if (c5.isSelected()) services += "Cheque Book, ";
                if (c6.isSelected()) services += "E-Statement";

                if (c7.isSelected()) {

                    Conn c = new Conn();
                    String query1 = "INSERT INTO signupthree (formno, accountType, accountNumber, PIN, services) values ('"
                                + formno + "', '" + accountType + "', '" + fullCardNumber + "', '" + fullPIN + "', '" + services + "')";
                    String query2 = "INSERT INTO login (formno, accountNumber, PIN) VALUES('" + formno + "', '" + fullCardNumber + "', '" + fullPIN + "')";

                    c.s.executeUpdate(query1);
                    c.s.executeUpdate(query2);
                    

                    JOptionPane.showMessageDialog(null, "Form Submitted\n" +
                            "Account Type: " + accountType + "\nServices: " + services +
                            "\nAccount Number: " + fullCardNumber + "\nPIN: " + fullPIN);

                    setVisible(false);
                    new Login().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please accept the declaration before submitting.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
            dispose();
        }
    }

    public static void main(String args[]) {
        new SignupThree("");
    }
}
