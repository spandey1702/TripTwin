// defines the interface components for 'My Chats' section on the UserProfile page. Loads messages from all other users as hyperlinks on the Window frame.

package View;
import javax.swing.*;

import Controller.ChatService;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.prefs.Preferences;

public class ChatPanel extends JFrame {
private JPanel chatPanel;
private JTextField messageField;
private JButton sendButton;
private Connection connection;
private ChatService chatService;
String receiver;

    public ChatPanel(String receiver)
     {
        this.receiver=receiver;
        setTitle("Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        addChatPanel();
        setVisible(true);
    }

    // Method to add chat panel
    private void addChatPanel()

     {
            
            chatPanel = new JPanel();
            chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

            // Text field for typing messages
            messageField = new JTextField(20);
            chatPanel.add(messageField);

            // Button to send messages
            sendButton = new JButton("Send");
            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendMessage();
                }
            });
            chatPanel.add(sendButton);

            JScrollPane scrollPane = new JScrollPane(chatPanel);
            add(scrollPane, BorderLayout.CENTER);
            
        }
    

    // Method to send a message
    private void sendMessage() 
    {
       ChatService chatService=new ChatService();
       chatService.sendMessage(retrieveUserID(),receiver,messageField.getText());
       JLabel sentMessageLabel = new JLabel("You: " + messageField.getText());
        chatPanel.add(sentMessageLabel);

    }
     
    private String retrieveUserID() 
    {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
        
    }
}
