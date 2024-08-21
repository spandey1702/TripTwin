package View;
import javax.swing.*;

import Controller.rating_review;

import java.awt.*;
import java.awt.event.*;
import java.util.prefs.Preferences;

public class UserRatingGUI extends JFrame 
{
    private JTextField ratingField;
    private JTextArea feedbackArea;
    private JButton rateButton;

    private String ratedUsername;

    public UserRatingGUI(String ratedUsername) 
    {
        this.ratedUsername = ratedUsername;

        setTitle("Rate User: " + ratedUsername); // Display the rated user's username in the title
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Rating (out of 5):"));
        ratingField = new JTextField();
        inputPanel.add(ratingField);
        inputPanel.add(new JLabel("Feedback:"));
        feedbackArea = new JTextArea();
        JScrollPane feedbackScrollPane = new JScrollPane(feedbackArea);
        inputPanel.add(feedbackScrollPane);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        rateButton = new JButton("Rate");
        rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rateUser();
            }
        });
        mainPanel.add(rateButton, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    private void rateUser() 
    {
        // Retrieve rating and feedback
        String ratingText = ratingField.getText();
        String feedback = feedbackArea.getText();

        try
        {
            double rating = Double.parseDouble(ratingText);
            if (rating < 0 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Rating must be between 0 and 5.", "Invalid Rating", JOptionPane.ERROR_MESSAGE);
                return;
        }

            // Update database with rating and feedback
            rating_review ratingReview = new rating_review(retrieveUserID(), ratedUsername); // Assuming the rater's email is known
            ratingReview.setrating(rating);
            ratingReview.setfeedback(feedback);
            ratingReview.updateDatabase();

            JOptionPane.showMessageDialog(this, "User rated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the rating window after rating
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating.", "Invalid Rating", JOptionPane.ERROR_MESSAGE);
        }
    }
private String retrieveUserID() 
{
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
}
}

