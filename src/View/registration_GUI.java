// defines the user interface of the Registration Page for the user.
package View;
import javax.swing.*;
import Controller.registration;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registration_GUI extends JFrame {
    private registration registration;
//Define Components and TextFields
    private JLabel firstNamelabel = new JLabel("First Name");
    private JTextField firstNamewfield = new JTextField(20);

    private JLabel lastName = new JLabel("Last Name");
    private JTextField lastNamefield = new JTextField(20);

    private JLabel emailID = new JLabel("Email Id");
    private JTextField emailTextfield = new JTextField(20);

    private JLabel username = new JLabel("Username");
    private JTextField usernamTextField = new JTextField(20);

    private JLabel Password = new JLabel("Password");
    private JPasswordField passwordField = new JPasswordField(20);

    private JLabel age = new JLabel("Age");
    private JTextField ageField = new JTextField(20);

    private JLabel gender = new JLabel("Gender");
    private JTextField genderField = new JTextField(20);

    private JLabel Address = new JLabel("Address");
    private JTextField Addressfield = new JTextField(20);

    private JButton registerButton = new JButton("Click to register");

    private JTextField output = new JTextField(20);
// load components in to layout 
    public registration_GUI() {
        // Setting layout to GridBagLayout
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        getContentPane().add(firstNamelabel, gbc);
        gbc.gridy++;
        getContentPane().add(lastName, gbc);
        gbc.gridy++;
        getContentPane().add(emailID, gbc);
        gbc.gridy++;
        getContentPane().add(username, gbc);
        gbc.gridy++;
        getContentPane().add(Password, gbc);
        gbc.gridy++;
        getContentPane().add(age, gbc);
        gbc.gridy++;
        getContentPane().add(gender, gbc);
        gbc.gridy++;
        getContentPane().add(Address, gbc);
        gbc.gridy++;

        gbc.gridx = 1;
        gbc.gridy = 0;
        getContentPane().add(firstNamewfield, gbc);
        gbc.gridy++;
        getContentPane().add(lastNamefield, gbc);
        gbc.gridy++;
        getContentPane().add(emailTextfield, gbc);
        gbc.gridy++;
        getContentPane().add(usernamTextField, gbc);
        gbc.gridy++;
        getContentPane().add(passwordField, gbc);
        gbc.gridy++;
        getContentPane().add(ageField, gbc);
        gbc.gridy++;
        getContentPane().add(genderField, gbc);
        gbc.gridy++;
        getContentPane().add(Addressfield, gbc);
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(registerButton, gbc);
        registerButton.addActionListener(new RegisterAction());

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(output, gbc);
        output.setEditable(false); // Make output field read-only
        output.setForeground(Color.BLUE);

        pack(); // Packs the components properly
        setVisible(true);
    }

    private class RegisterAction implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {  // retreive user entered info into fields to call JDBC code to add a new user
            String firstName = firstNamelabel.getText();
            String lastName = lastNamefield.getText();
            String emailId = emailTextfield.getText();
            String password =  passwordField.getText();
            String gender = genderField.getText();
            String address = Addressfield.getText();
            String username=usernamTextField.getText();
            int age = Integer.parseInt(ageField.getText());
            if (!isValid(emailId)) 
                 {
                        JOptionPane.showMessageDialog(null ,"Invalid email format",emailId, JOptionPane.ERROR_MESSAGE);
                        return;
                }
                else
                {
                    try {
                        //call JDBC code
                        registration=new registration();
                        registration.Registration(firstName, lastName, emailId, username, password, age, gender, address);
                    } catch (ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,"Connection Failed.Check details.",username, JOptionPane.ERROR);
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null,"Please enter all mandatory fields.",username, JOptionPane.ERROR);
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null,"Succesfully registered!" );
                }
            }
        }
    
// validate the correct email format
    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
