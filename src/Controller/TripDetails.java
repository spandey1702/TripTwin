package Controller;

import java.sql.*;
import java.util.ArrayList;

public class TripDetails {
    private String url = "jdbc:mysql://localhost:3306/final_project";
    private String user = "spandey";
    private String password = "Project@2024";

    public ArrayList<String> fetchTrips(String username) {
        ArrayList<String> trips = new ArrayList<>();
        String query = "SELECT destination FROM final_project.Trips WHERE Username = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the username parameter
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tripName = rs.getString("destination");
                trips.add(tripName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public String getTripDetails(String username, String tripName) {
        String tripDetails = "";
        String query = "SELECT * FROM final_project.Trips WHERE Username = ? AND destination = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the username and tripName parameters
            stmt.setString(1, username);
            stmt.setString(2, tripName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Assuming the database table has columns for trip details such as destination, start_date, end_date, etc.
                String destination = rs.getString("destination");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");
                
                // Construct trip details string
                tripDetails = "Destination: " + destination + "\nStart Date: " + startDate + "\nEnd Date: " + endDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripDetails;
    }
}