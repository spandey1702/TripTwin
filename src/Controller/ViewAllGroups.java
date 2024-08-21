// the class reterives all Groups created and stored in the database in order for user to select one of them if they need to join a Group.
package Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllGroups 
{
  private Connection connection;
    //establish Connection
        public ViewAllGroups() {
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
            } 
            catch (SQLException|ClassNotFoundException e) 
            {
                e.printStackTrace();
            }
        }
    
        // Method to fetch all groups a member is part of
        public ArrayList<String> getUserGroups() 
        {
            
            ArrayList<String> userGroups = new ArrayList<>();
            new ViewGroups();
            try {
                String query = "SELECT group_name FROM final_project.Groups";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) 
                {
                    String groupName = resultSet.getString("group_name");
                    userGroups.add(groupName);// add groups to ArrayList from DB.
                }
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userGroups;
        }
        
    } 



