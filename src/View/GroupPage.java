//Group Homepage to view the posts made by users

package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.GroupPosts;

public class GroupPage extends JFrame  {
    private JTextArea oldPostsTextArea;
    private JTextArea newPostTextArea;
    private JButton postButton;

    private GroupPosts groupPosts;
    private String groupname;

    public GroupPage(String groupname) {
        super(groupname);
        this.groupname = groupname;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
//Components
        // Initialize components
        oldPostsTextArea = new JTextArea(10, 40);
        newPostTextArea = new JTextArea(5, 40);
        postButton = new JButton("Post");

        groupPosts = new GroupPosts();
        groupPosts.connect(); // Connect to the database

        // Load existing posts
        refreshOldPosts();

        // Add components to the frame
        JPanel oldPostsPanel = new JPanel(new BorderLayout());
        oldPostsPanel.add(new JLabel("Old Posts:"), BorderLayout.NORTH);
        oldPostsPanel.add(new JScrollPane(oldPostsTextArea), BorderLayout.CENTER);
        add(oldPostsPanel, BorderLayout.NORTH);

        JPanel newPostPanel = new JPanel(new BorderLayout());
        newPostPanel.add(new JLabel("New Post:"), BorderLayout.NORTH);
        newPostPanel.add(new JScrollPane(newPostTextArea), BorderLayout.CENTER);
        add(newPostPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(postButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to post button
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPost = retrieveUserID() + ": " + newPostTextArea.getText();
                groupPosts.insertPost(newPost, retrieveUserID(), groupname); // Insert the new post into the database
                refreshOldPosts(); // Refresh the displayed old posts
                newPostTextArea.setText(""); // Clear the new post text area
            }
        });

        // Set frame properties
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void refreshOldPosts() {
        List<String> posts = groupPosts.getPosts();
        StringBuilder sb = new StringBuilder(oldPostsTextArea.getText());
        for (String post : posts) {
            sb.append(post).append("\n");
        }
        oldPostsTextArea.setText(sb.toString()); // Display existing old posts
    }

    private String retrieveUserID() {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }
}