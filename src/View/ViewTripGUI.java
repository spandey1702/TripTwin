// The interface defines the Action the "View Trips" button on Userprofile. Displays all trips in otder to find a Companion for them
package View;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import Controller.CompanionFinder;
import Controller.TripDetails;
import Controller.ViewTrip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class ViewTripGUI extends JFrame 
{
    private JEditorPane tripsEditorPane;
    
    
    public ViewTripGUI(String username) 
     {
        setTitle("TripTwin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
       

        tripsEditorPane = new JEditorPane();
        tripsEditorPane.setContentType("text/html"); // Set content type to HTML
        tripsEditorPane.setEditable(false);

        // Fetch trips for the logged-in user
        ViewTrip view=new ViewTrip();
        ArrayList<String> trips = view.fetchTrips(username);

        // Build HTML content for displaying trips as hyperlinks
        StringBuilder htmlContent = new StringBuilder("<html><body>");
        for (String trip : trips) 
        {
            htmlContent.append("<a href=\"").append(trip).append("\">").append(trip).append("</a><br>");
        }
        htmlContent.append("</body></html>");

        tripsEditorPane.setText(htmlContent.toString());
        setVisible(true);
       

        // Add hyperlink listener to handle clicks on trip links
        tripsEditorPane.addHyperlinkListener(new HyperlinkListener() 
        {
           
                TripDetails details=new TripDetails();
                public void hyperlinkUpdate(HyperlinkEvent e) 
                {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                 {
                    String selectedTrip = e.getDescription();
                    String tripDetails = details.getTripDetails(username, selectedTrip);
                    //JOptionPane.showMessageDialog(ViewTripGUI.this, tripDetails, "Trip Details", JOptionPane.INFORMATION_MESSAGE);
                   // JOptionPane.showMessageDialog(ViewTripGUI.this, "Trip details for: " + e.getURL());
                
                   JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());

        // Add trip details label
                     JLabel tripDetailsLabel = new JLabel(tripDetails);
                     panel.add(tripDetailsLabel, BorderLayout.CENTER);

        // Add "Find Companion" button
                JButton findCompanionButton = new JButton("Find Companion");
                
                findCompanionButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                // Handle Find Companion button action
                // Add your logic to find companions here
                // For example:
                JOptionPane.showMessageDialog(ViewTripGUI.this, "Finding companions for selected trip: " + selectedTrip);
                }
            
        });
        panel.add(findCompanionButton, BorderLayout.SOUTH);

        // Show the option pane with trip details and button
        JOptionPane.showMessageDialog(ViewTripGUI.this, panel, "Trip Details", JOptionPane.INFORMATION_MESSAGE);
                 }
                }
            });


        add(new JScrollPane(tripsEditorPane), BorderLayout.CENTER);
     }
    }
        
        


        
        /*private void findCompanions(String selectedTrip) 
        {
        CompanionFinder companionFinder=new CompanionFinder()
        List<String> companions = companionFinder.findCompanions(selectedTrip);
        StringBuilder message = new StringBuilder("Companions for selected trip:\n");
        for (String companion : companions) {
            message.append(companion).append("\n");
        
        JOptionPane.showMessageDialog(this, message.toString(), "Companions", JOptionPane.INFORMATION_MESSAGE);*/
    


    
     
    



  
   

