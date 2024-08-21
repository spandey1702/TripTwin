package View;
import javax.swing.*;
import javax.swing.border.Border;
import Controller.AuthenticateUser;
import Controller.UserProfileLogic;
import Model.User;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.prefs.Preferences;

public class Login 
{
private JFrame frame;
private JTextField userNameField;
private JPasswordField passwordField;
private JLabel userLabel, passwordLabel;
private JButton loginButton, registerButton;

public void LoginPage() 
{
    JFrame frame = new JFrame("TripTwin");
    frame.setSize(800, 600); 
    frame.setLayout(new BorderLayout());

        // Panel for login components
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setPreferredSize(new Dimension(200, 300)); 

        // Username panel
        JPanel usernamePanel = new JPanel(new GridLayout(2, 1));
        userLabel = new JLabel("Username");
        userNameField = new JTextField("Enter username");
        userNameField.setPreferredSize(new Dimension(100, 15)); 
        usernamePanel.add(userLabel);
        usernamePanel.add(userNameField);
        panel.add(usernamePanel);

        // Password panel
        JPanel passwordPanel = new JPanel(new GridLayout(2, 1));
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 15)); 
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        panel.add(passwordPanel);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(50, 10)); // Reduced button size
        loginButton.addActionListener(new LoginAction());
        panel.add(loginButton);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(50, 10)); // Reduced button size
        registerButton.addActionListener(new RegisterAction());
        panel.add(registerButton);

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Load image and add to the frame
        ImageIcon image = new ImageIcon("/Users/sagarikapandey/Documents/LabAssignments/Final project/OIP.jpg");
        Image scaledImage = image.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Smaller image size
        image = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(image);
        frame.add(imageLabel, BorderLayout.WEST);
        frame.setVisible(true);
    }

    // ActionListener for Login button
    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent e) 
    { 
        
    if(e.getSource()==loginButton)
     {   
        String username = userNameField.getText();
        char[] password = passwordField.getPassword();
        AuthenticateUser authUser=new AuthenticateUser();
        
        if (authUser.authenticateUser(username, password)==true) 
        {
            saveUserID(username);
            UserProfileLogic logic=new UserProfileLogic();
            User userProfile=logic.getUserProfile(username);
            userProfilePage page=new userProfilePage();
            page.display(userProfile);
        } 
        else 
        {
            JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
       
    }
}
}
// ActionListener for Register button
    private class RegisterAction implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            new registration_GUI();
        }
    }
// Method to save user ID to preferences
    private void saveUserID(String username) 
    {
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        prefs.put("userID", username);
    }
}