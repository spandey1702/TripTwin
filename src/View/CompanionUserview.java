// this class implements the UserProfileViewInterface to define the page for other users when they click on a userprofile. The retrictions are placed for Logged in User to certain actions in this way
package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.RatingFeedbackData;
import Controller.getProfilePicture;
import Model.User;

public class CompanionUserview extends JFrame implements UserprofileViewInterface
{
  //components
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JLabel bioLabel;
    private JLabel profilePictureLabel;
    private JButton chaButton;
    private User userProfile;
    private getProfilePicture getPicture;
    
   
    public void display(User userprofile)
    {
      this.userProfile = userprofile;
//mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel profilePanel = new JPanel(new GridLayout(6, 1, 10, 10));
//Userprofile details
        firstNameLabel = new JLabel("Name: " + userProfile.getFirstName());
        profilePanel.add(firstNameLabel);

        lastNameLabel = new JLabel("Name: " + userProfile.getLastName());
        profilePanel.add(lastNameLabel);

        emailLabel = new JLabel("Email: " + userProfile.getEmail());
        profilePanel.add(emailLabel);

        bioLabel = new JLabel("Bio: " + userProfile.getBio());
        bioLabel.setVerticalAlignment(SwingConstants.TOP);
        profilePanel.add(bioLabel);
 //Chat Button to enable communication    
        JPanel buttonJPanel=new JPanel(new GridLayout(2,1));
        chaButton=new JButton("Chat");
        buttonJPanel.add(chaButton);
        chaButton.addActionListener(new ActionListener()// ActionEvent for Chat Button
         {

          @Override
          public void actionPerformed(ActionEvent e) 
          {
             new ChatPanel(userProfile.getUserName());//chatPanel is opened to eneble user to send messages
          }
           
        });
//Rate Button
        JButton rateUserButton=new JButton("Rate User");
        buttonJPanel.add(rateUserButton);
        rateUserButton.addActionListener(new ActionListener() {// the rating page is opened to enabled Logged in user to rate the other users.

          @Override
          public void actionPerformed(ActionEvent e) {
            new UserRatingGUI(userProfile.getUserName());
          }
        });
//view Rating to enable user to View Ratings given by other users
        JButton viewRatings=new JButton("View Ratings");
        buttonJPanel.add(viewRatings);
        viewRatings.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e)
          {
            RatingFeedbackData feedback=new RatingFeedbackData();
             DefaultTableModel model=feedback.getRatingFeedbackData(userProfile.getUserName());
             new RatingFeedbackGUI(model);
            
          }
        });

        // Load Profile Picture of the User
        JPanel picturePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        profilePictureLabel = new JLabel();
        picturePanel.add(profilePictureLabel);

        mainPanel.add(profilePanel,BorderLayout.CENTER);
        mainPanel.add(picturePanel,BorderLayout.NORTH);
        mainPanel.add(buttonJPanel,BorderLayout.EAST);
        add(mainPanel);
        setProfilePicture();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }
    private void setProfilePicture() //call backend JDBC code to retreive picture
    {
           getPicture=new getProfilePicture();
           ImageIcon icon =getPicture.setProfilePicture(userProfile.getUserName());
           if(icon!=null)
           {
            
            Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            profilePictureLabel.setIcon(new ImageIcon(image));
           }
            
        }

        
      }
 
