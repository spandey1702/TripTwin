// the class defines the backend logic to retreive the Users profile picture from the database.

package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

public class getProfilePicture 
{
   private ImageIcon profileImageIcon = null;
   private Connection conn;
   
   //establish DB connection
   public getProfilePicture()
   {
   try 
   {   Class.forName("com.mysql.cj.jdbc.Driver");
       conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "spandey", "Project@2024");
   } 
   catch (SQLException | ClassNotFoundException e) 
   {
       e.printStackTrace();
   }
}
// method to retreive Profilepicture from Username . Accepts Username a s a parameter.
   public ImageIcon setProfilePicture(String Username) 
   {
     
    try 
        {
        String sql = "SELECT profilePicture FROM final_project.users WHERE Username= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, Username);

        // Execute query to retrieve profile picture
        ResultSet rs = pstmt.executeQuery();

        // Check if result set contains data
        if (rs.next()) {
            // Get the profile picture bytes from the result set
            byte[] imageBytes = rs.getBytes("profilePicture");

            // Convert image bytes to ImageIcon
            if (imageBytes != null) {
                profileImageIcon = new ImageIcon(imageBytes);
            }
        }

        // Close resources
        rs.close();
        pstmt.close();
        conn.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return profileImageIcon;
}
}
