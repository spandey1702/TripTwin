// the class defines the retreival logic for user details to load into the userprofile of the Users. The method returns USer details which are used in user Profile
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;

public class UserProfileLogic 
{
    private String url = "jdbc:mysql://localhost:3306/final_project";
    private String user = "spandey";
    private String password = "Project@2024";//establish DB Connection.
    private Connection con;
    public UserProfileLogic()
    {
            
            try {
                con=DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
    }
    public User getUserProfile(String username)// method to retreive details. Take s usernameas input to diffrenetiate users.
    {
        User userProfile=null;
        String query = "SELECT Username,First_Name,Last_Name,email,age,bio,Address FROM final_project.users WHERE Username = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                
                String firstName = resultSet.getString("First_Name");
                String lastName=resultSet.getString("Last_Name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String bio = resultSet.getString("bio");
                String address=resultSet.getString("Address");
                userProfile = new User(username,firstName,lastName , address, email,bio,age);
            }
            
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfile;

    }
    
}
