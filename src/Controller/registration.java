//the class defines the backend JDBC logic performed during Registration of a new user. The inputs are taken from the textfield in GUI and passed to registration method.

package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registration 
{
    private Connection connection;
    
        public registration() //establish DB Connection.
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
   //method to register new User   
 public void Registration(String firstName,String lastName,String emailId,String username,String password,int age,String gender,String address) throws ClassNotFoundException, SQLException
 {
            String sql = "INSERT INTO final_project.users (First_Name, Last_Name, Email, Password,Username, Age, Gender, Address) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, emailId);
            statement.setString(4, password);
            statement.setString(5, username);
            statement.setInt(6, age);
            statement.setString(7, gender);
            statement.setString(8, address);
            statement.executeUpdate();

            
            statement.close();
            connection.close();
        } 
           
}
    
    
    
            
