//the class reterives all the Trip details assciated with the USer to set them into a Dropdown.
package Controller;
import java.sql.*;
import java.util.ArrayList;

public class ViewAllTripDetails 
{
   private  ResultSet resultSet;
   private Connection connection;
   public ViewAllTripDetails() //establish DB Connection.
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
   

    public ResultSet getResultSet() 
    {
    return resultSet;
   }


public void setResultSet(ResultSet resultSet) 
{
    this.resultSet = resultSet;
}

//retreive Trips details based on username and destination
    public  ResultSet fetchTrips(String username,String destination) 
    {
         
         String query = "SELECT * FROM final_project.Trips where Username=? and destination=?"; // 
        try 
        {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2,destination);
             ResultSet rs = stmt.executeQuery();
            setResultSet(rs);

        } 
        catch (SQLException e) 
        {
       e.printStackTrace();
   }
   finally{
    try {
        connection.close();//close DB Connection
    } catch (SQLException e) {
        
        e.printStackTrace();
    }
   }
   return resultSet;
}
}

