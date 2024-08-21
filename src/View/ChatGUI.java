// defines the interface components for 'My Chats' section on the UserProfile page. Loads messages from all other users as hyperlinks on the Window frame.
package View;
import javax.swing.*;
import javax.swing.event.*;
import Controller.ChatService;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.prefs.Preferences;

public class ChatGUI extends JFrame 
{
    private JTextPane chatPane;
    private JTextField messageField;
    private JButton sendButton;
   private ChatService chatDatabase;
    private String username;

    public ChatGUI() 
    {
        
        setTitle("Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents(); // load Components
        loadUsernames(); //load messages into Components

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private String retrieveUserID() 
    {
        // Retrieve the user ID from preferences (or any other storage mechanism)
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        return prefs.get("userID", ""); // Return the user ID
    }
    //define GUI components for the page
    private void initComponents() 
    {
        JPanel mainPanel = new JPanel(new BorderLayout()); //container Panel
        chatPane = new JTextPane();
        chatPane.setContentType("text/html");
        chatPane.setEditable(false);
        // add ClickListener to load messgaed after User clicks on Usermessage
        chatPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) 
            {
                handleHyperlinkClick(e);// method to call after clicking the message Hyperlink. Loads messgae for the thread
            }

            private void handleHyperlinkClick(MouseEvent e) 
            {
                loadMessagesForUser(username);
                //retreive all messges received from the clicked
            }
         });

        JScrollPane scrollPane = new JScrollPane(chatPane);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        messagePanel.add(messageField, BorderLayout.CENTER);
       //Action Listener for sendMessgae button opn Panel
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                sendMessage();
            }
        });
        messagePanel.add(sendButton, BorderLayout.EAST);//add sent Messages to window

        mainPanel.add(messagePanel, BorderLayout.SOUTH);
       add(mainPanel);
    }
    private void loadMessagesForUser(String username2) 
    {
        StringBuilder messages = new StringBuilder();// define Strinbuilder to append all messages to window
        String messageDetails=null;
        chatDatabase=new ChatService();// call backend service to retreive Messages
        try 
        {
            messageDetails = chatDatabase.getMessageDetails(username2,retrieveUserID()) ;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        if (messageDetails != null) //apeend message to StrinBuilder messagedetails
            {
                messages.append(messageDetails);
                            
            }
            
            chatPane.setText(messages.toString());// add messages to Pane
        }   
        
    private void loadUsernames()  // the method loads all the Usernames as hyperlink who sre the sender of a messgae into the Panel
    {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        chatDatabase=new ChatService();// call backend to reteriveusername details
        ResultSet resultSet = chatDatabase.getMessages(retrieveUserID());
        if (resultSet != null) 
        {
            try 
            {
                while (resultSet.next()) 
                {
                    String username = resultSet.getString("senderUsername");
                    
                    htmlContent.append("<a href=\"").append(username).append("\">").append(username).append("</a><br>");
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        htmlContent.append("</body></html>");
        chatPane.setText(htmlContent.toString());
        chatPane.addHyperlinkListener(new HyperlinkListener() 
        {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) 
        {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
            {
                // When a hyperlink is clicked
                try 
                {
                     username = e.getDescription(); // Get the username from the hyperlink
                    // Now you can use the 'username' as needed
                    System.out.println("Clicked username: " + username);
                    
                    // Example: Open a web browser with the link
                    Desktop.getDesktop().browse(new java.net.URI(username));
                }
                catch ( Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    });
}
    private void sendMessage() 
    {
        String messageText = messageField.getText();
        if (!messageText.isEmpty()) 
        {
            chatDatabase.sendMessage(retrieveUserID(),username, messageText);
            loadMessagesForUser(username);
            messageField.setText("");
        }
    }
}
    

