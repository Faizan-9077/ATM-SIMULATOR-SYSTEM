import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {

        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("State Bank");
        bank.setBounds(150, 20, 200, 30);
        bank.setFont(new Font("Arial", Font.BOLD, 20));
        add(bank);

        JLabel account = new JLabel();
        account.setBounds(20, 60, 300, 20);
        add(account);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 500, 100, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
        });

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM login WHERE pin = '" + pinnumber + "'");
            if (rs.next()) {
                account.setText("Account No: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        String[] columnNames = {"Date", "Type", "Amount", "Balance"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);


        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(Color.LIGHT_GRAY);
                } else {
                    setBackground(Color.WHITE);
                }
                return this;
            }
        });


        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.GRAY);
        table.getTableHeader().setForeground(Color.WHITE);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);


        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);


        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM transactions WHERE pin ='" + pinnumber + "'");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                String balance = rs.getString("balance");

                model.addRow(new Object[]{date, type, amount, balance});
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 360, 350);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("1234").setVisible(true);
    }
}
