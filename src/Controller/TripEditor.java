// the class defines the code to enable users to edit their existing Trips. The new values are taken and updated into the DB.
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripEditor 
{
    private Connection connection;
    private int tripIdToUpdate;
    public TripEditor() //establish DB Connection.
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
    public void updateTrip(String newDestination, String newStartDate, String newEndDate) {
        try {
            
            PreparedStatement statement = connection.prepareStatement("UPDATE final_project.Trips SET destination=?, start_date=?, end_date=? WHERE tripId=?");
            statement.setString(1, newDestination);
            statement.setString(2, newStartDate);
            statement.setString(3, newEndDate);
            statement.setInt(4, tripIdToUpdate);
            statement.executeUpdate();
            // You can add more handling or feedback here if needed
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as required
        }
    }
}

