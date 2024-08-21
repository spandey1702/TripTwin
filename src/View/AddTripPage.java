//GUI class to define and load the attributes of Add Trip Page .
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import Controller.AddTripLogic;
import Controller.AuthenticateUser;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTripPage extends JFrame 
{
    private JTextField  destinationField, startDateField, endDateField, budgetField, descriptionField;
    
    
    private JButton saveTripButton;
    private JPanel panel;
    private User user;
    
    public AddTripPage() {
        setTitle("Add Trip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(7, 2));//container Panel

        

        panel.add(new JLabel("Destination:"));
        destinationField = new JTextField();
        panel.add(destinationField); //destination Panel

        panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField();
        startDateField.setText("YYYY-MM-DD");
        startDateField.setForeground(Color.LIGHT_GRAY);
        panel.add(startDateField);//start Date Panel

        panel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField();
        endDateField.setText("YYYY-MM-DD");
        endDateField.setForeground(Color.LIGHT_GRAY);
        panel.add(endDateField); // end Date Panel

        panel.add(new JLabel("Budget:"));
        budgetField = new JTextField();
        panel.add(budgetField);//Budget panel

        panel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        panel.add(descriptionField); //desciption Panel

       saveTripButton=new JButton("Save");
        saveTripButton.addActionListener(new ButtonAction());
        panel.add(saveTripButton); //saveTrip Panel

        add(panel);
        setVisible(true);
    }
    //Action Listener method. Calls the backend class to run and insert the new Trip added by USer.
private class ButtonAction implements ActionListener
{
    public void actionPerformed(ActionEvent e) 
    {// retreive user inputs
            String username= retrieveUserID();
            String destination = destinationField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            double budget = Double.parseDouble(budgetField.getText());
            String description = descriptionField.getText();

            // Call JDBC code to insert into database
            new AddTripLogic(username,destination, startDate, endDate, budget, description);
            JOptionPane.showMessageDialog(panel,"Upcoming Trip Updated");
          
    }
    
}
private String retrieveUserID() {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }

    private void clearFields() {
        
        destinationField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        budgetField.setText("");
        descriptionField.setText("");
    }



}

