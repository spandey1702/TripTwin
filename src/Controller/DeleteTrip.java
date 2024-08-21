// the class defines the backend logic to update user's itinerary by letting them delete their added trips.
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTrip 
{
    private String url = "jdbc:mysql://localhost:3306/final_project";
    private String user = "spandey";
    private String password = "Project@2024";
    private Connection conn;
    private PreparedStatement stmt;
    public DeleteTrip() //establish DB Connection
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
    //method to delete a Trip. Takes TripID as a paramter and destination as a paramter to delete.
    public void DeleteTripDetails(int TripID,String destination)
    {
    try 
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
         // Opening database connection to MySQL server
        conn = DriverManager.getConnection(url, user, password);
        String sql = "DELETE FROM final_project.Trips WHERE TripID= ? AND destination=?"; 
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, TripID);
        stmt.setString(2,destination);
        stmt.executeUpdate();
    } 
    catch (SQLException | ClassNotFoundException ex) 
    {
                ex.printStackTrace();
    } 
    finally 
    {
                // Closing JDBC objects
                try {
                    if (stmt != null)
                        stmt.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    