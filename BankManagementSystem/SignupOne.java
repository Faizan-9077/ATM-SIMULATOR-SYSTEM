import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignupOne extends JFrame implements ActionListener {

    JLabel formnoLabel;
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    JRadioButton male, female, other, married, unmarried;
    SpinnerDateModel dateModel;
    JSpinner dateSpinner;
    JSpinner.DateEditor dateEditor;

    SignupOne() {
        setLayout(null);

        formnoLabel = new JLabel();
        formnoLabel.setFont(new Font("Arial", Font.BOLD, 38));
        formnoLabel.setBounds(140, 20, 600, 40);
        add(formnoLabel);

        updateFormNumber();

        setSize(850, 800);
        setLocation(350, 10);
        setUndecorated(true);
        setVisible(true);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Arial", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Arial", Font.BOLD, 22));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Arial", Font.BOLD, 22));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Arial", Font.BOLD, 22));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(300, 240, 200, 30);
        add(dateSpinner);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Arial", Font.BOLD, 22));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 290, 120, 30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Arial", Font.BOLD, 22));
        email.setBounds(100, 340, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Arial", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Arial", Font.BOLD, 22));
        marital.setBounds(100, 390, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 100, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBounds(630, 390, 100, 30);
        other.setBackground(Color.WHITE);
        add(other);

        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(unmarried);
        maritalgroup.add(other);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Arial", Font.BOLD, 22));
        address.setBounds(100, 440, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Arial", Font.BOLD, 14));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Arial", Font.BOLD, 22));
        city.setBounds(100, 490, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Arial", Font.BOLD, 14));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        JLabel state = new JLabel("State:");
        state.setFont(new Font("Arial", Font.BOLD, 22));
        state.setBounds(100, 540, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Arial", Font.BOLD, 14));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        JLabel pincode = new JLabel("Pin Code:");
        pincode.setFont(new Font("Arial", Font.BOLD, 22));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        pinTextField.setBounds(300, 590, 400, 30);
        add(pinTextField);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Arial", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    private void updateFormNumber() {
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
        formnoLabel.setText("APPLICATION FORM NO. " + randomNum);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = formnoLabel.getText().split(" ")[3];
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dob = dateFormat.format(dateSpinner.getValue());
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String email = emailTextField.getText();
        String marital = null;
        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        } else if (other.isSelected()) {
            marital = "Other";
        }

        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pinTextField.getText();

        if (name.equals("") || !name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Invalid Name: Only alphabets and spaces allowed.");
            return;

        } if (fname.equals("") || !fname.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Invalid Father's Name: Only alphabets and spaces allowed.");
            return; // Stop execution if father's name is not filled
        } if (email.equals("") || !validateEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid Email.");
            return;
        } else if (dob.equals("")) {
            JOptionPane.showMessageDialog(null, "Date of Birth is required");
            return;
        } else if (address.equals("")) {
            JOptionPane.showMessageDialog(null, "Address is required");
            return;
        } else if (city.equals("")) {
            JOptionPane.showMessageDialog(null, "City is required");
            return;
        } else if (state.equals("")) {
            JOptionPane.showMessageDialog(null, "State is required");
            return;
        } if (pin.equals("") || !pin.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "Invalid Pin Code: Must be 6 digits.");
            return;
        }

        try {
            Conn c = new Conn();
            String checkQuery = "SELECT * FROM signup WHERE formno = '" + formno + "' OR email = '" + email + "'";
            ResultSet rs = c.s.executeQuery(checkQuery);

            if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record already exists!");
                    clearForm();
            } else {
                String query = "INSERT INTO signup VALUES('" + formno + "', '" + name + "', '" + fname + "', '" + dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + state + "', '" + pin + "')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }
        }
        
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearForm() {
        nameTextField.setText("");
        fnameTextField.setText("");
        emailTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        pinTextField.setText("");
        dateSpinner.setValue(new Date());
        male.setSelected(false);
        female.setSelected(false);
        married.setSelected(false);
        unmarried.setSelected(false);
        other.setSelected(false);
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

        public static void main (String args[]) {
            new SignupOne();
        }
    }
        
   
