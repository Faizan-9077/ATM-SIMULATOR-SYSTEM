import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    JTextField pin, repin;
    JButton change, back;

    PinChange(String pinchange) {

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 800);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLACK);
        text.setBounds(280, 230, 500, 30);
        image.add(text);

        JLabel pintext = new JLabel("NEW PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setBackground(Color.BLACK);
        pintext.setBounds(165, 290, 180, 25);
        image.add(pintext);

        pin = new JTextField();
        pin.setFont(new Font("Raleway",Font.BOLD, 19));
        pin.setBounds(330, 290, 180, 21);
        image.add(pin);

        JLabel repintext = new JLabel("Re-Enter NEW PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setBackground(Color.BLACK);
        repintext.setBounds(165, 330, 180, 25);
        image.add(repintext);

        repin = new JTextField();
        repin.setFont(new Font("Raleway",Font.BOLD, 19));
        repin.setBounds(330, 330, 180, 21);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(410, 465, 100, 25);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(163, 465, 100, 25);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 800);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {
    try{
        String npin = pin.getText();
        String rpin = repin.getText();

    }
    catch (Exception e) {
        System.out.println(e);
    }
    }

    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
