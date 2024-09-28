import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JLabel formnoLabel;
    JTextField panTextField, aadhar;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, category, income, occupation, education;
    String formno;

    SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel rel = new JLabel("Religion:");
        rel.setFont(new Font("Raleway", Font.BOLD, 22));
        rel.setBounds(100, 140, 100, 30);
        add(rel);

        String valReligion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel categ = new JLabel("Category:");
        categ.setFont(new Font("Raleway", Font.BOLD, 22));
        categ.setBounds(100, 190, 200, 30);
        add(categ);

        String valCategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(valCategory);
        category.setBounds(300, 190, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        JLabel inc = new JLabel("Income:");
        inc.setFont(new Font("Raleway", Font.BOLD, 22));
        inc.setBounds(100, 240, 200, 30);
        add(inc);

        String incomeCategory[] = {"Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "< 10,00,000", "More than 10,00,000"};
        income = new JComboBox(incomeCategory);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        JLabel edu = new JLabel("Educational");
        edu.setFont(new Font("Raleway", Font.BOLD, 22));
        edu.setBounds(100, 290, 200, 30);
        add(edu);

        JLabel qual = new JLabel("Qualification:");
        qual.setFont(new Font("Raleway", Font.BOLD, 22));
        qual.setBounds(100, 315, 200, 30);
        add(qual);

        String qualifications[] = {"High School", "Diploma", "Bachelor's Degree", "Master's Degree", "Ph.D.", "Other"};
        education = new JComboBox(qualifications);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel occu = new JLabel("Occupation:");
        occu.setFont(new Font("Raleway", Font.BOLD, 22));
        occu.setBounds(100, 390, 200, 30);
        add(occu);

        String occupationalVal[] = {"Salaried", "Self-Employed", "Business Owner", "Student", "Retired", "Unemployed", "Other"};
        occupation = new JComboBox(occupationalVal);
        occupation.setBounds(300, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel pan = new JLabel("PAN Number:");
        pan.setFont(new Font("Raleway", Font.BOLD, 22));
        pan.setBounds(100, 440, 200, 30);
        add(pan);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        panTextField.setBounds(300, 440, 400, 30);
        add(panTextField);

        JLabel aadh = new JLabel("Aadhar Number:");
        aadh.setFont(new Font("Raleway", Font.BOLD, 22));
        aadh.setBounds(100, 490, 200, 30);
        add(aadh);

        aadhar = new JTextField();
        aadhar.setFont(new Font("Raleway", Font.BOLD, 14));
        aadhar.setBounds(300, 490, 400, 30);
        add(aadhar);

        JLabel senior = new JLabel("Senior Citizen:");
        senior.setFont(new Font("Raleway", Font.BOLD, 22));
        senior.setBounds(100, 540, 200, 30);
        add(senior);

        syes = new JRadioButton("Yes");
        syes.setBounds(300, 540, 100, 30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(450, 540, 100, 30);
        sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);

        JLabel existAcc = new JLabel("Existing Account:");
        existAcc.setFont(new Font("Raleway", Font.BOLD, 22));
        existAcc.setBounds(100, 590, 200, 30);
        add(existAcc);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 590, 100, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(450, 590, 100, 30);
        eno.setBackground(Color.WHITE);
        add(eno);

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(eyes);
        accountGroup.add(eno);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String seniorcitizen = syes.isSelected() ? "Yes" : "No";
        String existingaccount = eyes.isSelected() ? "Yes" : "No";
        String span = panTextField.getText();
        String saadhar = aadhar.getText();

        if (span.equals("") || saadhar.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        seducation = seducation.replace("'", "''");

        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo values ('" + formno + "', '" + sreligion + "', '" + scategory + "', '" + sincome + "', '" + seducation + "', '" + soccupation + "', '" + span + "', '" + saadhar + "', '" + seniorcitizen + "', '" + existingaccount + "')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details added successfully");
            setVisible(false);
            new SignupThree(formno).setVisible(true);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("");
    }
}
