//the class defines the backend logic to authenticate the users. When the user enters the credentials to the Login page, the class checks for the entered info and compares to DB info to dertermine a succesfull authentication.

package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;

public class AuthenticateUser 
{
    private boolean authStatus=false;
    private int userid;
    private String passString=null;
    private String storedPassword=null;
    private Connection conn;
    
    
    public AuthenticateUser() //form database connection;
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
//method to check succesfull authentication. Compares the enetred password by the user with the password stored in the DB
    public  boolean authenticateUser(String username, char[] password) 
    {
        try
        {
            passString=new String(password);
            String query = "SELECT Password,User_ID FROM final_project.users WHERE Username=?";//SQL Query for auth
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                userid=resultSet.getInt("User_ID");
                storedPassword=resultSet.getString("Password");
            }
            
        
        }
        catch (SQLException e) 
    {
            e.printStackTrace();
    }
    finally
    {
        try 
        {
            conn.close(); //conn.close
        } 
        catch (SQLException e) 
        {
            
            e.printStackTrace();
        }
    }
return passString.equals(storedPassword); // returns a boolean value to determine authentication.
}

public int getuserid()
{
   return userid;
}
}