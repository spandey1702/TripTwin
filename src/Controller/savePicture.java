//class to save the chosen Picture by the user to the DB.
package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class savePicture 
{
    private Connection connection;
    public savePicture() //establish DB Connection.
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
            } 
            catch (SQLException | ClassNotFoundException e) 
            {
                e.printStackTrace();
            }
        }
        //method to set Picture. Takes the path from filechooser and image as inputs.
    public void saveProfilePicture(String username, String imagePath) 
    {
            try
            {
                File imageFile = new File(imagePath);
                FileInputStream fis = new FileInputStream(imageFile);

                // Establish connection to database
              
                // Prepare SQL statement
                String sql = "UPDATE final_project.users SET profilePicture = ? WHERE Username = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setBinaryStream(1, fis);
                pstmt.setString(2, username);

                // Execute SQL statement
                pstmt.executeUpdate();

                // Close resources
                pstmt.close();
                fis.close();
                connection.close();
            } 
            catch 
            (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


