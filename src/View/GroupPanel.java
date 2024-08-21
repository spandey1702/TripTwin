// defines the User Interface for "View Groups" button on UserProfilePage. Enables them to view to go to any page chosen
package View;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

import java.awt.*;
import java.util.List;
import java.util.prefs.Preferences;

public class GroupPanel extends JFrame {

    private JTextPane textPane;
    private String groupName;

    public GroupPanel(List<String> groupNames) 
    {
        super("My Groups");

        // Set up the GUI components
        textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Display group names as hyperlinks
        StringBuilder html = new StringBuilder();
        for (String groupName : groupNames) {
            html.append("<a href=\"#\">")
                .append(groupName).append("</a><br>");
        }

        // Set the HTML content to the text pane
        textPane.setText(html.toString());

        // Add action listener to hyperlinks
        textPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
            {
                groupName = extractGroupName(e.getDescription());
                new GroupPage(groupName);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String extractGroupName(String description) 
    {
        return description;
    }
    
    

    
}