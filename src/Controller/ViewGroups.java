// the class contains the back End Logic to define the list of Groups associated with a User. The groups are polulated in the User Interface for user to perform opeartions.
package Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
    
    public class ViewGroups {
        private Connection connection;
    
        public ViewGroups() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
            } catch (SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    
        // Method to fetch all groups a member is part of
        public List<String> getUserGroups(String username) {
            List<String> userGroups = new ArrayList<>();
            new ViewGroups();
            try {
                String query = "SELECT group_name FROM final_project.GroupMembers WHERE member_name = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String groupName = resultSet.getString("group_name");
                    userGroups.add(groupName);
                }
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userGroups;
        }
        
    } 

