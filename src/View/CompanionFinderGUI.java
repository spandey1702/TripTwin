// the class defines the interface for Companion Finder page. It loads all Trips associated with aUSer into dropdown and aListener is activated to find a companion.

package View;
import javax.swing.*;

import Controller.CompanionFinder;
import Controller.UserProfileLogic;
import Controller.ViewTrip;
import Model.User;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

// GUI class for the trip companion finder
class CompanionFinderGUI extends JFrame 
{
    private JComboBox<String> tripsDropdown;
    private JPanel companionsPanel;
    private ViewTrip view;
    private JButton findComapnionButton; 
    private CompanionFinder finder;
    
    private String retrieveUserID()
     {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }
    
    public CompanionFinderGUI() {
        setTitle("TripTwin");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Dropdown for trips
        tripsDropdown = new JComboBox<>();
        setTrips();
        panel.add(tripsDropdown, BorderLayout.NORTH);

        findComapnionButton=new JButton("Find TripTwin");
        findComapnionButton.addActionListener(new CompanionFinderListener());
        panel.add(findComapnionButton,BorderLayout.PAGE_END);
        
        // Panel to display companions as hyperlinks
        companionsPanel = new JPanel();
        companionsPanel.setLayout(new BoxLayout(companionsPanel, BoxLayout.Y_AXIS));
        panel.add(new JScrollPane(companionsPanel), BorderLayout.CENTER);

        // Add panel to frame
        add(panel);

        // Show the GUI
        setVisible(true);
    }

    // Method to set trips in the dropdown
    public void setTrips() 
    {  view=new ViewTrip();
        List<String> trips=view.fetchTrips(retrieveUserID());
        for (String trip : trips) {
            tripsDropdown.addItem(trip);
        }
    }

    // Method to display companions as hyperlinks
    private class CompanionFinderListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            finder=new CompanionFinder();
            ArrayList<String>companions=finder.findCompanion(getSelectedTrip(),retrieveUserID());
            System.out.println(companions);
            for (String companion : companions) {
            JLabel hyperlink = new JLabel(companion);
            hyperlink.setForeground(Color.BLUE);
            hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
            companionsPanel.setToolTipText(companion.toString());

            // Add action listener to open user profile
            hyperlink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    
                        JLabel clickedLabel = (JLabel) e.getSource();
                        String labelText = clickedLabel.getText();
                        UserProfileLogic up = new UserProfileLogic();
                        User userprofile = up.getUserProfile(labelText);
                        saveReceiverUserID(labelText);
                        CompanionUserview companionGUI = new CompanionUserview();
                        companionGUI.display(userprofile);
                    
                }
            });

            companionsPanel.add(hyperlink);
            companionsPanel.add(Box.createVerticalStrut(5));
        }

        companionsPanel.revalidate();
        companionsPanel.repaint();
        
    }

    
    

    // Method to add action listener to trips dropdown
    public void addTripsDropdownListener(ActionListener listener) 
    {
        tripsDropdown.addActionListener(listener);
    }

    // Method to get selected trip
    public String getSelectedTrip() 
    {
        return (String) tripsDropdown.getSelectedItem();
    }
    
}
private void saveReceiverUserID(String receiverUsername) 
        {
          // Save user ID to preferences
          Preferences prefs = Preferences.userNodeForPackage(CompanionFinderGUI.class);
          prefs.put("ReceiveruserID", receiverUsername);

        }
        
}