// the class performs the function of Add Member to the groups that they decide to join. The class defines the backend JDBC opertion to achive this functionality. Once the method is called, the User become of the member of the chosen group
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddGroupMemeber 
{
    private Connection connection;

    public AddGroupMemeber() //establish the connection to DB
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
    //the method defines the query to a add the memeber to the DB.
    public void addMember(String groupName, String memberName) {
        try {
            String insertQuery = "INSERT INTO final_project.GroupMembers (group_name, member_name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, groupName);
            preparedStatement.setString(2, memberName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
