// the class defines the backend logic to enable the user to create a New Group. The new Group is created as per details and inserted to DB.
package Controller;
import java.sql.*;
public class CreateGroup 
{
    private Connection connection;

    public CreateGroup() //establish DB Connection
    {
        try 
        {   Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
        } 
        catch (SQLException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    // Method to create a new group
    public void createGroup(String groupName) 
    {
        try {
            String insertQuery = "INSERT INTO final_project.Groups (group_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, groupName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            connection.close();//close connection
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
