//the class defines the methods to post nad reterive updates to the group in form of texts. A connection is formed to the database and the post is inserted and associated  with the group and user.
package Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupPosts {
    private Connection connection;

    public void connect() {
        try {
            // Connect to  database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void insertPost(String post,String username,String groupname) 
    {
        try 
        {
            // Insert the post into  database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO final_project.GroupPosts (Username,group_name,post_text) VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, groupname);
            statement.setString(3, post);
            statement.executeUpdate();
            } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<String> getPosts() 
    {
        List<String> posts = new ArrayList<>();
        try {
            // Retrieve posts from  database
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT post_text,Username FROM final_project.GroupPosts");
             while (resultSet.next()) 
            {
                String post = resultSet.getString("post_text");
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
}

