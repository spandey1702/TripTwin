//the class defines the methods to post updates to the group in form of texts. A connection is formed to the database and the post is inserted and associated  with the group and user.
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPost 
{
    private Connection connection;

    public AddPost() //form database connection;
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
    public void addGroupPost(String groupName, String post) //add the post into the database.
    {
        String sql = "INSERT INTO final_project.GroupPosts (group_name, post_text) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
         {
            statement.setString(1, groupName);
            statement.setString(2, post);
            statement.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
}
}
