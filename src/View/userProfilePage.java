// defines the UserProfile page view for the Logged in user. Contains basic info and the buttons to perform certain user Action line Update Trips, Add Trips, Create and Join Groups, Upload Picture, View Chats.
package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import Controller.RatingFeedbackData;
import Controller.SearchMember;
import Controller.ViewGroups;
import Controller.getProfilePicture;
import Controller.savePicture;

import java.util.List;
public class userProfilePage extends JFrame implements UserprofileViewInterface
{ //define Components
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel bioLabel;
    private JLabel emailLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JButton addTripButton;
    private JButton viewTripButton;
    private JButton logOffButton;
    private JButton createGroupButton;
    private JButton viewGroupsButton;
    private JLabel profilePictureLabel; 
    private JButton choosePictureButton;
    private JButton myChatsButton;
    private JButton joinGroups;
    private JButton editTrips;
    private JTextField searchField;
    private JButton searchButton;
    private User userprofile;
    private savePicture save;
    private getProfilePicture getPicture;

    private String retrieveUserID() 
{
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }
   
    public void display(User userProfile)
    {
       
        this.userprofile = userProfile;
//Outer container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel profilePanel = new JPanel(new GridLayout(6, 1, 10, 10));
        
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchActionListener());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel,BorderLayout.PAGE_START);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        firstNameLabel = new JLabel("Name: " + userProfile.getFirstName());
        profilePanel.add(firstNameLabel);
// define Components for User details
        lastNameLabel = new JLabel("Name: " + userProfile.getLastName());
        profilePanel.add(lastNameLabel);

        emailLabel = new JLabel("Email: " + userProfile.getEmail());
        profilePanel.add(emailLabel);

        ageLabel = new JLabel("Age: " + userProfile.getAge());
        profilePanel.add(ageLabel);

        bioLabel = new JLabel("Bio: " + userProfile.getBio());
        bioLabel.setVerticalAlignment(SwingConstants.TOP);
        profilePanel.add(bioLabel);

        addressLabel=new JLabel("Address"+userProfile.getAddress());
        profilePanel.add(addressLabel);
        mainPanel.add(profilePanel, BorderLayout.CENTER);

        // Panel to hold profile picture
        JPanel picturePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        profilePictureLabel = new JLabel();
        picturePanel.add(profilePictureLabel);
// profile picture button
        choosePictureButton = new JButton("Choose Picture");
        choosePictureButton.addActionListener(new ChoosePictureActionListener());
        picturePanel.add(choosePictureButton);

        mainPanel.add(picturePanel, BorderLayout.NORTH);
// panle to Hold all the buttons
        JPanel buttonPanel=new JPanel(new GridLayout(5, 1, 10, 10));
        addTripButton=new JButton("Add Trip");
        addTripButton.addActionListener(new ButtonAction());
        buttonPanel.add(addTripButton);
        

        viewTripButton=new JButton("View Trips");
        viewTripButton.addActionListener(new ViewAction());
        buttonPanel.add(viewTripButton);

        logOffButton=new JButton("Log Out");
        logOffButton.addActionListener(new logOffActionListener());
        buttonPanel.add(logOffButton);

        createGroupButton=new JButton("Create New Group");
        createGroupButton.addActionListener(new createGroupAction());
        buttonPanel.add(createGroupButton);

        viewGroupsButton=new JButton("My Groups");
        viewGroupsButton.addActionListener(new viewGroupsAction());
        buttonPanel.add(viewGroupsButton);

        myChatsButton=new JButton("My Chats");
        myChatsButton.addActionListener(new myChatsAction());
        buttonPanel.add(myChatsButton);

        joinGroups=new JButton("Join New Group");
        joinGroups.addActionListener(new myReviewsAction());
        buttonPanel.add(joinGroups);

        editTrips=new JButton("Edit Trips");
        editTrips.addActionListener(new EditActionListener());
        buttonPanel.add(editTrips);



        mainPanel.add(buttonPanel,BorderLayout.EAST);
        add(mainPanel);
        getPicture=new getProfilePicture();
        setProfilePicture();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
// call JDBC code to reterive and set the Profile picture
    private void setProfilePicture() 
    {
           getPicture=new getProfilePicture();
           ImageIcon icon =getPicture.setProfilePicture(retrieveUserID());
           if(icon!=null)
           {
            
            Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            profilePictureLabel.setIcon(new ImageIcon(image));
           }
            
        }
   // call JDBC code to insert a new Picture uploaded by a User
    private class ChoosePictureActionListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(userProfilePage.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                userprofile.setProfilePicturepath(imagePath);
                setProfilePicture();
                save=new savePicture();
                save.saveProfilePicture(retrieveUserID(),imagePath);
                getPicture.setProfilePicture(retrieveUserID());
            }
        }
    }
    // Button Action Add Trip button
    private class ButtonAction  implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
       new AddTripPage();
    }
}  
// Button Action for edit Trips
private class EditActionListener implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
       new TripSelectionPage();
    }
}  
//Button Action for Mychats
private class myChatsAction implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
       new ChatGUI();
    }
}  
//Button Action for MyReviews
private class myReviewsAction implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
       new GroupDropdown();
    }
}  
//Button Action forn viewGroups
private class viewGroupsAction  implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
        ViewGroups view=new ViewGroups();
        List<String>groupnameList=new ArrayList<>();
        groupnameList=view.getUserGroups(retrieveUserID());
        new GroupPanel(groupnameList);
    
    }
}  

// ButtonAction to imoplement Companion Finder and View Trips to find Companions
    
    private class ViewAction implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
        //ViewTripGUI view=new ViewTripGUI(retrieveUserID());
        //view.show();
        new CompanionFinderGUI();
    }

}
// Create Group Page Action. Calls the interface for Create Group Page
private class createGroupAction implements ActionListener 
{ 
    public void actionPerformed(ActionEvent e) 
    {
       new craeteGroupPage();
    }
} 
//Logg  off retruns to sign In
private class logOffActionListener implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {
      new Login();
   }
}
private class SearchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchTerm = searchField.getText().trim();
            if (!searchTerm.isEmpty()) {
                // Perform search
                SearchMember searchMember = new SearchMember();
                List<String> searchResults = searchMember.searchMembers(searchTerm);
                // Display search results
                if (!searchResults.isEmpty()) {
                    StringBuilder resultText = new StringBuilder("Search Results:\n");
                    for (String result : searchResults) {
                        resultText.append(result).append("\n");
                    }
                    JOptionPane.showMessageDialog(userProfilePage.this, resultText.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(userProfilePage.this, "No results found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(userProfilePage.this, "Please enter a search term.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        

public void displayProfilePicture(ImageIcon profilePicture) 
{
    profilePictureLabel.setIcon(profilePicture);
}







}
}



