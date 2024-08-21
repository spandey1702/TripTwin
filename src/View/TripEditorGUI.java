// define the User Interface to edit exiting Trips for a user. The page loads the existing Trip details in an editable format for user to change
package View;
import javax.swing.*;

import Controller.TripEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
public class TripEditorGUI extends JFrame  {
private JTextField destinationField, startDateField, endDateField;
private JButton updateButton;
private TripEditor dbHandler;

    public TripEditorGUI(int tripId, String destination, Date startDate, Date endDate) 
    {
        super("Edit Trip");
        
//Container Panel
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
  //define Components and add to MainPanel     
        mainPanel.add(new JLabel("Destination:"));
        destinationField = new JTextField(destination);
        mainPanel.add(destinationField);

        mainPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField(startDate.toString());
        mainPanel.add(startDateField);

        mainPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField(endDate.toString());
        mainPanel.add(endDateField);

        updateButton = new JButton("Update Trip");// button to generate Update event
        updateButton.addActionListener(new UpdateAction());// Update Action calls JDBC code to update the data in DB and notify user
        mainPanel.add(updateButton);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);// set the frame as Visible
        setVisible(true);
    }

    // Action to perform for Update Trip Button
    private class UpdateAction implements ActionListener
    {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String newDestination = destinationField.getText();// retreive Updated info
            String newStartDate = startDateField.getText();
            String newEndDate = endDateField.getText();

            // Update trip in the database
            dbHandler=new TripEditor();
            dbHandler.updateTrip(newDestination, newStartDate, newEndDate);
            JOptionPane.showMessageDialog(null,"Trip Updated!");
        }
    }
}
}

