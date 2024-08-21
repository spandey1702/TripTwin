//the class drives the beckend logic to retreive Trip destination only associated with a user. This is used to populate dropdowns.
package Controller;
import java.sql.*;
import java.util.ArrayList;
public class ViewTrip 
{
    private Connection connection;
    public ViewTrip() //establish DB Connection.
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
        //method to retrieve details.returns an ArrayList of Trips associated with user.
    public  ArrayList<String> fetchTrips(String username) 
    {
        String query = "SELECT destination FROM final_project.Trips where Username=?"; // 
        ArrayList<String> trips = new ArrayList<>();
        try 
        {
            
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                String tripName = rs.getString("destination");
                trips.add(tripName);
            }
   } catch (SQLException e) 
   {
       e.printStackTrace();
   }
   finally
   {
     try 
    {
        connection.close();
    } 
    catch (SQLException e) 
    {
       
        e.printStackTrace();
    }
   }
   return trips;
}
}
