
//user Interface to create a New gRoup for a user.
package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controller.CreateGroup;

public class craeteGroupPage extends JFrame
{
    private JLabel groupName;
    private JTextField groupnameField;
    private JButton saveButton;
    private JPanel panel;

    public craeteGroupPage()
    {
        
        setTitle("Create New Group");
        panel=new JPanel(new GridLayout(3,1));
        groupName=new JLabel("Enter Group name");
        groupnameField=new JTextField();// textField to enter Group name
        saveButton=new JButton("Save");
        saveButton.addActionListener(new saveListener());// creates new Group
        panel.add(groupName);
        panel.add(groupnameField);
        panel.add(saveButton);
        add(panel);
        setVisible(true);
        
    }  
        private class saveListener implements ActionListener
        { 
              @Override
            public void actionPerformed(ActionEvent e) //call JDBC code to insert the new Group details in to the DB
            {
                CreateGroup create=new CreateGroup();
                create.createGroup(groupnameField.getText());
                if(groupnameField!=null)
                {
                    JOptionPane.showMessageDialog(panel,"New Group Created");

                }
                else
                {
                    JOptionPane.showMessageDialog(panel,"Group name is a required field" );
                }
                
            }
    
        }
    }

